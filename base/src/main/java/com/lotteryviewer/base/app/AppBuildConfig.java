package com.lotteryviewer.base.app;

import android.os.Bundle;

import androidx.annotation.Nullable;

/**
 * @Author: duke
 * @DateTime: 2021-03-17 11:55:01
 * @Description: 功能描述：
 */
public class AppBuildConfig {

    private static boolean DEBUG = Boolean.parseBoolean("false");
    private static String APPLICATION_ID = "";
    private static String MAIN_ACTIVITY_NAME = "";
    private static String BUILD_TYPE = "debug";
    private static String FLAVOR = "none";
    private static int VERSION_CODE = 1;
    private static String VERSION_NAME = "1.0";

    private static Bundle EXTRAS = new Bundle();


    public static boolean DEBUG() {
        return DEBUG;
    }

    public static String APPLICATION_ID() {
        return APPLICATION_ID;
    }

    public static String MAIN_ACTIVITY_NAME() {
        return MAIN_ACTIVITY_NAME;
    }

    public static String BUILD_TYPE() {
        return BUILD_TYPE;
    }

    public static String FLAVOR() {
        return FLAVOR;
    }

    public static int VERSION_CODE() {
        return VERSION_CODE;
    }

    public static String VERSION_NAME() {
        return VERSION_NAME;
    }

    public static Bundle EXTRAS() {
        return EXTRAS;
    }

    @Nullable
    @SuppressWarnings("unchecked")
    public static <T> T EXTRA(String key) {
        return (T) EXTRAS.get(key);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static class Builder {

        public Builder DEBUG(boolean val) {
            DEBUG = val;
            return this;
        }

        public Builder APPLICATION_ID(String val) {
            APPLICATION_ID = val;
            return this;
        }

        public Builder MAIN_ACTIVITY_NAME(String val) {
            MAIN_ACTIVITY_NAME = val;
            return this;
        }

        public Builder BUILD_TYPE(String val) {
            BUILD_TYPE = val;
            return this;
        }

        public Builder FLAVOR(String val) {
            FLAVOR = val;
            return this;
        }

        public Builder VERSION_CODE(int val) {
            VERSION_CODE = val;
            return this;
        }

        public Builder VERSION_NAME(String val) {
            VERSION_NAME = val;
            return this;
        }

        public Builder EXTRAS(Bundle bundle) {
            EXTRAS.putAll(bundle);
            return this;
        }
    }

}
