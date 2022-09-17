package com.lotteryviewer.base.util;

import android.util.Log;

import java.util.Locale;

/**
 * @Author: duke
 * @DateTime: 2022-09-16 14:58:39
 * @Description: 功能描述：
 */
public enum AndroidVersion {


    Android_("", "", 33),
    Android_API_32("Android API 32", "Sv2", 32),
    Android_12_0("Android 12.0", "S", 31),
    Android_11_0("Android 11.0", "R", 30),
    Android_10_0("Android 10.0", "Q", 29),
    Android_9_0("Android 9.0", "Pie", 28),
    Android_8_1("Android 8.1", "Oreo", 27),
    Android_8_0("Android 8.0", "Oreo", 26),
    Android_7_1_1("Android 7.1.1", "Nougat", 25),
    Android_7_0("Android 7.0", "Nougat", 24),
    Android_6_0("Android 6.0", "Marshmallow", 23),
    Android_5_1("Android 5.1", "Lollipop", 22),
    Android_5_0("Android 5.0", "Lollipop", 21),
    Android_4_4W("Android 4.4W", "KitKat Wear", 20),
    Android_4_4("Android 4.4", "KitKat", 19),
    Android_4_3("Android 4.3", "Jelly Bean", 18),
    Android_4_2("Android 4.2", "Jelly Bean", 17),
    Android_4_1("Android 4.1", "Jelly Bean", 16),
    Android_4_0_3("Android 4.0.3", "IceCreamSandwich", 15),
    Android_4_0("Android 4.0", "IceCreamSandwich", 14),
    Android_3_2("Android 3.2", "Honeycomb", 13),
    Android_3_1("Android 3.1", "Honeycomb", 12),
    Android_3_0("Android 3.0", "Honeycomb", 11),
    Android_2_3_3("Android 2.3.3", "Gingerbread", 10),
    Android_2_3("Android 2.3", "Gingerbread", 9),
    Android_2_2("Android 2.2", "Froyo", 8),
    Android_2_1("Android 2.1", "Eclair", 7),
    ;

    private final String versionName;
    private final String alias;
    private final int apiLevel;

    public String getVersionName() {
        return versionName;
    }

    public String getAlias() {
        return alias;
    }

    public int getApiLevel() {
        return apiLevel;
    }

    AndroidVersion(String versionName, String alias, int apiLevel) {
        this.versionName = versionName;
        this.alias = alias;
        this.apiLevel = apiLevel;
    }

    private static boolean isNullOrEmpty(String text) {
        if (text == null) {
            return true;
        }
        text = text.trim();
        return text.length() == 0;
    }

    private static String textTrim(String text) {
        if (text == null) {
            return null;
        }
        return text.trim();
    }

    /**
     * 依据 系统版本名称，查找对应的 系统版本 对象信息
     *
     * @param versionName 系统版本名称。如：Android 10.0
     * @return 系统版本信息对象。如：Android_2_1('Android 2.1', 'Eclair', 7)
     */
    public static AndroidVersion findByVersionName(String versionName) {
        versionName = textTrim(versionName);
        if (isNullOrEmpty(versionName)) {
            return null;
        }
        versionName = versionName.toLowerCase(Locale.getDefault())
                .replaceAll("android", "")
                .trim();
        versionName = "Android " + versionName;

        AndroidVersion[] enumArr = values();
        for (AndroidVersion item : enumArr) {
            if (item == null || isNullOrEmpty(item.getVersionName())) {
                continue;
            }
            if (item.getVersionName().equals(versionName)
                    || item.getVersionName().equals(versionName + ".0")) {
                return item;
            }
        }
        return null;
    }

    /**
     * 依据 系统版本号，查找对应的 系统版本 对象信息
     *
     * @param apiLevel 系统版本号。如：30
     * @return 系统版本信息对象。如：Android_2_1('Android 2.1', 'Eclair', 7)
     */
    public static AndroidVersion findByAPILevel(int apiLevel) {
        AndroidVersion[] enumArr = values();
        for (AndroidVersion item : enumArr) {
            if (item == null) {
                continue;
            }
            if (item.getApiLevel() == apiLevel) {
                return item;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "AndroidVersion{" +
                "versionName='" + versionName + '\'' +
                ", alias='" + alias + '\'' +
                ", apiLevel=" + apiLevel +
                '}';
    }

    public static void test() {
        Log.d("ver_test", String.valueOf(AndroidVersion.findByAPILevel(30)));
        Log.d("ver_test", String.valueOf(AndroidVersion.findByVersionName("12")));
    }

}
