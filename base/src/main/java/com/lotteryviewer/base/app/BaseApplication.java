package com.lotteryviewer.base.app;

import android.app.Application;

/**
 * @Author: duke
 * @DateTime: 2021-03-17 10:53:57
 * @Description: 功能描述：
 */
public class BaseApplication extends Application {

    private static Application REAL_INSTANCE;
    private static Application sApplicationContext;

    {
        setApplication(this);
    }

    private static void setApplication(Application application) {
        sApplicationContext = application;
        if (application instanceof BaseApplication) {
            REAL_INSTANCE = (BaseApplication) application;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        ActivityStack.init(get());
    }

    public static Application get() {
        if (REAL_INSTANCE != null) {
            return REAL_INSTANCE;
        }
        if (sApplicationContext != null) {
            return sApplicationContext;
        }
        throw new NullPointerException("Application 为空");
    }

}
