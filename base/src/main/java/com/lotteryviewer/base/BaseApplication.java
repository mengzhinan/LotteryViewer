package com.lotteryviewer.base;

import android.app.Application;

/**
 * @Author: duke
 * @DateTime: 2021-03-17 10:53:57
 * @Description: 功能描述：
 */
public class BaseApplication {

    private static Application INSTANCE;

    public static void injecctApp(Application application) {
        if (application == null) {
            throw new NullPointerException("Application 为空");
        }
        INSTANCE = application;
        ActivityStack.init(application);
    }

    public static Application get() {
        if (INSTANCE == null) {
            throw new NullPointerException("Application 为空");
        }
        return INSTANCE;
    }

}
