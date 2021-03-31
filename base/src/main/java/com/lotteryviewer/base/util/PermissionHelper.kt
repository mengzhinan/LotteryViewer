package com.lotteryviewer.base.util

import android.annotation.TargetApi
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import java.lang.ref.WeakReference
import java.util.*

/**
 * @Author: duke
 * @DateTime: 2019-03-12 13:33
 * @Description: 请求权限代理类
 * Version：3.0
 * Modify：2021-03-31
 */
class PermissionHelper private constructor(fragmentManager: FragmentManager) {

    private val mFragmentWeakReference: WeakReference<DFragment>?
    private var mDCallback: DCallback? = null

    fun setCallback(dCallback: DCallback?): PermissionHelper {
        mDCallback = dCallback
        val fragment = currentFragment
        if (fragment != null) {
            fragment.mDCallback = dCallback
        }
        return this
    }

    private val currentFragment: DFragment?
        get() {
            val fragment = mFragmentWeakReference?.get()
            return if (fragment == null || !fragment.isAdded || fragment.isDetached) {
                null
            } else fragment
        }

    /**
     * 过滤无效的权限
     *
     * @param permissions 用户设置的权限
     * @return 去除重复、无效后的权限
     */
    private fun filterPermissions(vararg permissions: String): Array<String> {
        if (isNullOrEmpty(permissions)) {
            return arrayOf()
        }
        val set = HashSet<String>()
        for (permission in permissions) {
            if (isNullOrEmpty(permission)) {
                continue
            }
            set.add(permission)
        }
        val resultArray = Array(set.size) { "" }
        set.toArray(resultArray)
        return resultArray
    }

    /**
     * 上层请求权限入口方法
     *
     * @param permissions 待申请的权限
     */
    @TargetApi(Build.VERSION_CODES.M)
    fun startRequest(vararg permissions: String) {
        // 过滤
        val newPermissions = filterPermissions(*permissions)
        if (isNullOrEmpty(newPermissions)) {
            return
        }
        val notRequestList = ArrayList<PermissionInfo>(newPermissions.size)
        val needRequestList = ArrayList<String>(newPermissions.size)
        for (permission in newPermissions) {
            if (isNullOrEmpty(permission)) {
                continue
            }
            if (isGranted(permission!!)) {
                // 已经授权，isGranted = true
                notRequestList.add(
                    PermissionInfo(
                        permission,
                        isGranted = true,
                        isShouldShowRequestPermissionRationale = false
                    )
                )
                continue
            }
            // 还是再次请求一次吧
//            if (isRevoked(permission)) {
//                // 没有授权，但是用户勾选了不再提醒，isGranted = false
//                notRequestList.add(new PermissionInfo(permission, false, false));
//                continue;
//            }
            needRequestList.add(permission)
        }
        if (isNullOrEmpty(needRequestList)) {
            // 已经全部是不允许或者是被系统策略拒绝的权限
            // 就算调用 requestPermissions(string, int)，系统也不会回调 onRequestPermissionsResult() 函数
            // 此情况，只好自己向上层返回
            val resultList = ArrayList<PermissionInfo>()
            if (!isNullOrEmpty(notRequestList)) {
                resultList.addAll(notRequestList)
            }
            if (mDCallback != null) {
                mDCallback!!.onResult(resultList)
            }
        } else {
            // 请求权限(有为允许且可能被用户允许的权限，需要向系统申请)
            // 由底层一层返回结果
            requestPermissionsFromFragment(notRequestList, needRequestList.toTypedArray())
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    private fun requestPermissionsFromFragment(
        notRequestList: ArrayList<PermissionInfo>,
        permissions: Array<String>
    ) {
        val fragment = currentFragment ?: return
        // 保存已经允许或者系统拒绝的权限，底层一起返回
        fragment.notRequestList.clear()
        if (!isNullOrEmpty(notRequestList)) {
            fragment.notRequestList.addAll(notRequestList)
        }
        // 底层请求权限
        fragment.requestPermissions(*permissions)
    }

    /**
     * 如果已经授权，则返回true。
     * 如果 SDK < 23，则永远返回true。
     */
    private fun isGranted(permission: String): Boolean {
        val fragment = currentFragment
        return !isMarshmallow || fragment?.isGranted(permission) == true
    }

    /**
     * 如果权限已被策略撤销，则返回true。
     * 如果 SDK < 23 ，则永远返回false。
     */
    private fun isRevoked(permission: String): Boolean {
        val fragment = currentFragment
        return isMarshmallow && fragment?.isRevoked(permission) == true
    }

    /**
     * 是否是 >= 23
     *
     * @return 是否需要动态权限适配
     */
    private val isMarshmallow: Boolean
        get() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M

    private fun isNullOrEmpty(str: String?): Boolean {
        return str == null || str.trim().isEmpty()
    }

    private fun isNullOrEmpty(arr: Array<*>?): Boolean {
        return arr == null || arr.isEmpty()
    }

    private fun isNullOrEmpty(list: List<*>?): Boolean {
        return list == null || list.isEmpty()
    }

    //============================================================

    /**
     * @Author: duke
     * @DateTime: 2019-03-11 15:12
     * @Description: 请求权限fragment
     */
    class DFragment : Fragment() {

        val notRequestList: ArrayList<PermissionInfo> = arrayListOf()
        var mDCallback: DCallback? = null

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            retainInstance = true
        }

        @TargetApi(Build.VERSION_CODES.M)
        fun requestPermissions(vararg permissions: String) {
            // 底层请求权限的方法
            requestPermissions(permissions, PERMISSIONS_REQUEST_CODE)
        }

        @TargetApi(Build.VERSION_CODES.M)
        override fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<String>,
            grantResults: IntArray
        ) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)

            // 系统权限回调
            if (requestCode != PERMISSIONS_REQUEST_CODE) {
                return
            }
            val permissionResultList = ArrayList<PermissionInfo>(permissions.size)
            for (permission in permissions) {
                permissionResultList.add(
                    PermissionInfo(
                        permission,
                        isGranted(permission),
                        shouldShowRequestPermissionRationale(permission)
                    )
                )
                // 如果想了解跟多该方法的含义，请查看 PermissionInfo 类对应属性说明
                // //shouldShowRequestPermissionRationale()
            }
            if (notRequestList.isNotEmpty()) {
                // 累加上层已经确定的权限
                permissionResultList.addAll(notRequestList)
            }

            mDCallback?.onResult(permissionResultList)
        }

        @TargetApi(Build.VERSION_CODES.M)
        fun isGranted(permission: String): Boolean {
            val activity = activity
                ?: throw NullPointerException("Exception caused by fragment detached from activity.")

            return ActivityCompat.checkSelfPermission(
                activity,
                permission
            ) == PackageManager.PERMISSION_GRANTED
        }

        @TargetApi(Build.VERSION_CODES.M)
        fun isRevoked(permission: String): Boolean {
            val activity: Activity? = activity
            if (activity == null || activity.isFinishing || activity.isDestroyed) {
                throw NullPointerException("Exception caused by fragment detached from activity.")
            }

            val packageManager = activity.packageManager
                ?: throw NullPointerException("Exception caused by activity.getPackageManager() == null.")

            val packageName = activity.packageName
                ?: throw NullPointerException("Exception caused by activity.getPackageName() == null.")

            return packageManager.isPermissionRevokedByPolicy(permission!!, packageName)
        }

        companion object {
            private const val PERMISSIONS_REQUEST_CODE = 11111
            val TAG_FRAGMENT = PermissionHelper::class.java.name.hashCode().toString()
        }
    }

    //============================================================

    /**
     * @Author: duke
     * @DateTime: 2019-03-11 15:10
     * @Description: 权限 bean
     */
    class PermissionInfo(
        // 权限名称
        var name: String?,
        // 是否授权
        var isGranted: Boolean,
        /**
         * 是否需要向用户解释此权限功能。
         * 官网说明：https://developer.android.google.cn/training/permissions/requesting.html
         *
         *
         * 返回 true 情况：
         * 当用户 仅仅只是 拒绝 某项权限时，此方法返回 true。but，看 false 情况。
         *
         *
         * 注意，返回 false 情况：
         * 如果用户在过去拒绝了权限请求，并在权限请求系统对话框中选择了 Don't ask again 选项，此方法将返回 false。
         * 如果设备规范禁止应用具有该权限，此方法也会返回 false。
         */
        var isShouldShowRequestPermissionRationale: Boolean
    ) {

        override fun equals(o: Any?): Boolean {

            // 内存地址比较
            if (this === o) {
                return true
            }
            return (o is PermissionInfo
                    && javaClass == o.javaClass
                    && name == o.name
                    && isGranted == o.isGranted
                    && isShouldShowRequestPermissionRationale == o.isShouldShowRequestPermissionRationale)

        }
    }

    //============================================================

    /**
     * @Author: duke
     * @DateTime: 2019-03-11 15:02
     * @Description: 权限请求回调
     */
    interface DCallback {
        fun onResult(permissionInfoList: ArrayList<PermissionInfo>?)
    }

    companion object {
        fun newInstance(activity: FragmentActivity): PermissionHelper {
            return PermissionHelper(activity.supportFragmentManager)
        }

        fun newInstance(fragment: Fragment): PermissionHelper {
            return PermissionHelper(fragment.childFragmentManager)
        }
    }

    init {
        var dFragment = fragmentManager.findFragmentByTag(DFragment.TAG_FRAGMENT) as DFragment?
        if (dFragment == null) {
            dFragment = DFragment()
            fragmentManager
                .beginTransaction()
                .add(dFragment, DFragment.TAG_FRAGMENT)
                .commitNow()
        }
        mFragmentWeakReference = WeakReference(dFragment)
    }
}