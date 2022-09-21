package com.lotteryviewer.base.util;

import android.os.Build;

/**
 * @Author: duke
 * @DateTime: 2022-09-19 10:32:13
 * @Description: 功能描述：
 */
public class AndroidInfo {

    /**
     * 手机品牌
     *
     * @return 如：Xiaomi
     */
    public static String getPhoneBrand() {
        return Build.BRAND;
    }

    /**
     * 手机制造商
     *
     * @return 如：Xiaomi
     */
    public static String getPhoneManufacturer() {
        return Build.MANUFACTURER;
    }

    /**
     * 手机型号
     *
     * @return 如：M2002J9E
     */
    public static String getPhoneModel() {
        return Build.MODEL;
    }

    /**
     * Android 系统版本
     *
     * @return 如：8.1、12 等
     */
    public static String getAndroidSystemVersion() {
        return Build.VERSION.RELEASE;
    }

    /**
     * Android SDK 版本
     *
     * @return 如：31
     */
    public static int getAndroidSDKVersion() {
        return Build.VERSION.SDK_INT;
    }

}
