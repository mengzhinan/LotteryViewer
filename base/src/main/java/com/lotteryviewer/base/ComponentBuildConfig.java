package com.lotteryviewer.base;

import android.os.Bundle;

/**
 * @Author: duke
 * @DateTime: 2021-03-17 11:55:01
 * @Description: 功能描述：
 */
public class ComponentBuildConfig {
    protected static boolean DEBUG = Boolean.parseBoolean("false");
    /**
     * 是否是以组件形态运行，默认为 true，也就是说在不配置的情况下，认为处于非完整形态
     */
    protected static boolean IS_MODULAR = Boolean.parseBoolean("true");
    protected static boolean IS_64_BIT = Boolean.parseBoolean("false");
    protected static String APPLICATION_ID = "com.zhihu.android";
    protected static String BUILD_TYPE = "debug";
    protected static String FLAVOR = "none";
    protected static String DIMENSION = "none";
    protected static String CHANNEL = "none";
    protected static String APP_CLOUD_ID = "none";
    protected static int VERSION_CODE = 1;
    protected static String VERSION_NAME = "1.0";
    protected static String INSTALLER_ID = "module";
    protected static String[] SO_ABI = null;
    protected static String MAIN_ACTIVITY_NAME = "com.zhihu.android.app.ui.activity.MainActivity";
    protected static Bundle EXTRAS = new Bundle();

    /**
     * 是否是以组件形态运行，默认为 true，也就是说在不配置的情况下，认为处于非完整形态
     */
    public static boolean IS_MODULAR() {
        return IS_MODULAR;
    }

    public static boolean IS_64_BIT() {
        return IS_64_BIT;
    }

    public static boolean DEBUG() {
        return DEBUG;
    }

    public static String APPLICATION_ID() {
        return APPLICATION_ID;
    }

    public static String BUILD_TYPE() {
        return BUILD_TYPE;
    }

    public static String DIMENSION() {
        return DIMENSION;
    }
    public static String FLAVOR() {
        return FLAVOR;
    }

    public static String CHANNEL() {
        return CHANNEL;
    }

    public static String APP_CLOUD_ID() {
        return APP_CLOUD_ID;
    }

    public static int VERSION_CODE() {
        return VERSION_CODE;
    }

    public static String VERSION_NAME() {
        return VERSION_NAME;
    }

    public static String INSTALLER_ID() {
        return INSTALLER_ID;
    }

    /**
     * 当前 app 使用的 so 的架构类型，如果不设置则为 null，否则为 armeabi-v7a 等值
     */
    public static String[] SO_ABI() {
        return SO_ABI;
    }

    public static String MAIN_ACTIVITY_NAME() {
        return MAIN_ACTIVITY_NAME;
    }

    /**
     * 扩展配置
     * @return {@link Bundle} 赋值参见：com.zhihu.android.app.ConfigCreator#initBuildConfig
     */
    public static Bundle EXTRAS() {
        return EXTRAS;
    }

    public static Builder newComponentBuilder() {
        return new Builder();
    }

    public static class Builder {

        public Builder IS_MODULAR(boolean val) {
            IS_MODULAR = val;
            return this;
        }

        public Builder IS_64_BIT(boolean val) {
            IS_64_BIT = val;
            return this;
        }

        public Builder DEBUG(boolean val) {
            DEBUG = val;
            return this;
        }

        public Builder APPLICATION_ID(String val) {
            APPLICATION_ID = val;
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

        public Builder DIMENSION(String val) {
            DIMENSION = val;
            return this;
        }

        public Builder CHANNEL(String val) {
            CHANNEL = val;
            return this;
        }

        public Builder APP_CLOUD_ID(String val) {
            APP_CLOUD_ID = val;
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

        public Builder MAIN_ACTIVITY_NAME(String val) {
            MAIN_ACTIVITY_NAME = val;
            return this;
        }

        public Builder INSTALLER_ID(String val) {
            INSTALLER_ID = val;
            return this;
        }

        public Builder SO_ABI(String[] val) {
            SO_ABI = val;
            return this;
        }

        public Builder APPEND_EXTRA(Bundle bundle) {
            EXTRAS.putAll(bundle);
            return this;
        }
    }
}
