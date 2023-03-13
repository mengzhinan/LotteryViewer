package com.lotteryviewer.base.util

import android.content.Context
import android.os.Build

/**
 * @Author: duke
 * @DateTime: 2022-09-19 10:32:13
 * @Description: 功能描述：
 */
object AndroidInfo {

    fun getWidthDp2(context: Context?): Float {
        return getWidthPx(context) / getDensity(context)
    }

    fun getWidthDp(context: Context?): Float {
        return getWidthPx(context) * (160.0f / getDensityDpi(context))
    }

    fun getHeightDp(context: Context?): Float {
        return getHeightPx(context) * (160.0f / getDensityDpi(context))
    }

    fun getDensityDpi(context: Context?): Int {
        val dm = context?.resources?.displayMetrics
        return dm?.densityDpi ?: 0
    }

    fun getDensity(context: Context?): Float {
        val dm = context?.resources?.displayMetrics
        return dm?.density ?: 0.0F
    }

    fun getHeightPx(context: Context?): Int {
        val dm = context?.resources?.displayMetrics
        return dm?.heightPixels ?: 0
    }

    fun getWidthPx(context: Context?): Int {
        val dm = context?.resources?.displayMetrics
        return dm?.widthPixels ?: 0
    }

    fun getVersionCode(context: Context?): Long {
        val pm = context?.applicationContext?.packageManager
        val pInfo = pm?.getPackageInfo(getPackageName(context), 0)
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            pInfo?.longVersionCode ?: 0L
        } else {
            pInfo?.versionCode?.toLong() ?: 0L
        }
    }

    fun getVersionName(context: Context?): String {
        val pm = context?.applicationContext?.packageManager
        val pInfo = pm?.getPackageInfo(getPackageName(context), 0)
        return pInfo?.versionName ?: ""
    }

    fun getPackageName(context: Context?): String {
        return context?.applicationContext?.packageName ?: ""
    }

    /**
     * 手机品牌
     *
     * @return 如：Xiaomi
     */
    val phoneBrand: String
        get() = Build.BRAND

    /**
     * 手机制造商
     *
     * @return 如：Xiaomi
     */
    val phoneManufacturer: String
        get() = Build.MANUFACTURER

    /**
     * 手机型号
     *
     * @return 如：M2002J9E
     */
    val phoneModel: String
        get() = Build.MODEL

    /**
     * Android 系统版本
     *
     * @return 如：8.1、12 等
     */
    val androidSystemVersion: String
        get() = Build.VERSION.RELEASE

    /**
     * Android SDK 版本
     *
     * @return 如：31
     */
    val androidSDKVersion: Int
        get() = Build.VERSION.SDK_INT

    /**
     * App 的 TargetSdk 版本
     *
     * @return 如：32
     */
    fun getAppTargetSdk(context: Context?): Int {
        return context?.applicationInfo?.targetSdkVersion ?: -1
    }

}
