package com.lotteryviewer.base.util

import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import androidx.annotation.RequiresApi

/**
 * @Author: duke
 * @DateTime: 2021-03-17 12:25:51
 * @Description: 功能描述：
 */
object PackageUtil {

    private fun getPackageInfo(context: Context?): PackageInfo? {
        try {
            return context?.packageManager?.getPackageInfo(context.packageName, 0)
        } catch (e: Exception) {
            // do nothing
        }
        return null
    }

    fun getPackageName(context: Context?): String? {
        return getPackageInfo(context)?.packageName
    }

    @RequiresApi(Build.VERSION_CODES.P)
    fun getVersionCode(context: Context?): Long? {
        return getPackageInfo(context)?.longVersionCode
    }

    fun getVersionName(context: Context?): String? {
        return getPackageInfo(context)?.versionName
    }

    fun getFirstInstallTime(context: Context?): Long? {
        return getPackageInfo(context)?.firstInstallTime
    }

    fun getLastUpdateTime(context: Context?): Long? {
        return getPackageInfo(context)?.lastUpdateTime
    }

    fun getBitmap(context: Context?): Bitmap? {
        var packageManager: PackageManager?
        var applicationInfo: ApplicationInfo?
        var bm: Bitmap? = null
        try {
            packageManager = context?.applicationContext?.packageManager ?: return null
            applicationInfo = packageManager.getApplicationInfo(context.packageName, 0)
            val d = packageManager.getApplicationIcon(applicationInfo)
            val bd = d as BitmapDrawable
            bm = bd.bitmap
        } catch (e: Exception) {
            // do nothing
        }
        return bm
    }

    /**
     * 获取应用程序名称
     */
    fun getAppName(context: Context?): String? {
        try {
            val packageInfo = getPackageInfo(context)
            val labelRes = packageInfo?.applicationInfo?.labelRes ?: 0
            return context?.resources?.getString(labelRes)
        } catch (e: Exception) {
            // do nothing
        }
        return null
    }

    /**
     * 根据 name 获得 MetaData 里值。
     *
     *
     * 比如：
     *
     *
     * <meta-data android:name="LEANCLOUD_ID" android:value="${LEANCLOUD_ID}"></meta-data>
     *
     * @param name meta-data 里的 name
     * @return 如果没有此值，那么返回 null。
     */
    fun getMetaData(context: Context?, name: String?): String? {
        try {
            val appInfo = context?.packageManager?.getApplicationInfo(
                context.packageName,
                PackageManager.GET_META_DATA
            ) ?: return null

            val obj = appInfo.metaData[name]
            var valueMeta: String? = null
            if (obj != null) {
                valueMeta = obj.toString()
            }
            return valueMeta
        } catch (e: Exception) {
            // ignored
        }
        return null
    }
}