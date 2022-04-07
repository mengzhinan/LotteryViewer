package com.lotteryviewer.base.util;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.os.Process;
import android.text.TextUtils;
import android.util.Log;

import com.lotteryviewer.base.app.SimpleActivityLifecycleCallbacks;

/**
 * @Author: duke
 * @DateTime: 2022-02-28 17:44:37
 * @Description: 功能描述：
 * 1、测试工具类，不要删除了
 * 2、为什么不用 Kotlin 写？在 Futureve 中也要使用，编译有点问题
 */
public class ActivityLifecycleLog {

    public static final String TAG = "activity_log";

    private static String safeTAG(String tag) {
        if (tag == null) {
            tag = TAG;
        } else {
            tag = tag.trim();
        }
        return tag;
    }

    private static void printLog(String tag, Activity activity, String methodName, boolean isLogE) {
        if (methodName == null || activity == null || TextUtils.isEmpty(tag)) {
            return;
        }
        String aName = activity.getClass().getName();
        String aMsg = methodName + "()  activityName = " + aName;
        doLog(tag, aMsg, isLogE);

        String aMsg2 = "Activity 进程堆栈信息  pid = " + Process.myPid() + " taskId = " + activity.getTaskId();
        doLog(tag, aMsg2, isLogE);

//        if (activity instanceof BaseFragmentActivity) {
//            Fragment fragment = ((BaseFragmentActivity) activity).getCurrentDisplayFragment();
//            if (fragment != null) {
//                String fName = fragment.getClass().getName();
//                String fMsg = methodName + "() fragmentName = " + fName;
//                doLog(tag, fMsg, isLogE);
//
//                String fMsg2 = "Fragment 进程堆栈信息  pid = " + Process.myPid() + " taskId = " + activity.getTaskId();
//                doLog(tag, fMsg2, isLogE);
//            }
//        }
        doLog(tag, "--------------------------------", isLogE);
    }

    private static void doLog(String tag, String msg, boolean isLogE) {
        if (isLogE) {
            Log.e(tag, msg);
        } else {
            Log.d(tag, msg);
        }
    }

    public static void registerActivityLifecycleCallbacks(Application application) {
        registerActivityLifecycleCallbacks(application, null);
    }

    public static void registerActivityLifecycleCallbacks(Application application, String logTag) {
        registerActivityLifecycleCallbacks(application, logTag, false, false);
    }

    public static void registerActivityLifecycleCallbacks(Application application, boolean isLogE) {
        registerActivityLifecycleCallbacks(application, null, isLogE, false);
    }

    /**
     * 注册 Activity 回调，打印所有 Activity 生命周期信息
     *
     * @param application             app
     * @param logTag                  log 名
     * @param isLogE                  是否需要打出 E 级别 Log
     * @param isJustOnCreateDestroyed 是否需要打出所有生命周期方法信息，还是仅仅 create 和 destroy
     */
    public static void registerActivityLifecycleCallbacks(Application application, String logTag, boolean isLogE, boolean isJustOnCreateDestroyed) {
        if (application == null) {
            return;
        }

        String finalLogTag = safeTAG(logTag);

        application.registerActivityLifecycleCallbacks(new SimpleActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                super.onActivityCreated(activity, savedInstanceState);
                printLog(finalLogTag, activity, "onCreated", isLogE);
            }

            @Override
            public void onActivityStarted(Activity activity) {
                super.onActivityStarted(activity);
                if (isJustOnCreateDestroyed) {
                    return;
                }
                printLog(finalLogTag, activity, "onStarted", isLogE);
            }

            @Override
            public void onActivityResumed(Activity activity) {
                super.onActivityResumed(activity);
                if (isJustOnCreateDestroyed) {
                    return;
                }
                printLog(finalLogTag, activity, "onResumed", isLogE);
            }

            @Override
            public void onActivityPaused(Activity activity) {
                super.onActivityPaused(activity);
                if (isJustOnCreateDestroyed) {
                    return;
                }
                printLog(finalLogTag, activity, "onPaused", isLogE);
            }

            @Override
            public void onActivityStopped(Activity activity) {
                super.onActivityStopped(activity);
                if (isJustOnCreateDestroyed) {
                    return;
                }
                printLog(finalLogTag, activity, "onStopped", isLogE);
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                super.onActivityDestroyed(activity);
                printLog(finalLogTag, activity, "onDestroyed", isLogE);
            }
        });
    }

    public static void activityLog(Activity activity, String methodName) {
        activityLog(activity, methodName, null);
    }

    public static void activityLog(Activity activity, String methodName, String logTag) {
        activityLog(activity, methodName, logTag, false);
    }

    public static void activityLog(Activity activity, String methodName, boolean isLogE) {
        activityLog(activity, methodName, null, isLogE);
    }

    /**
     * 打印 Activity 某个方法的 Log 信息
     *
     * @param activity   activity
     * @param methodName 方法名
     * @param logTag     log 名
     * @param isLogE     是否需要打出 E 级别 Log
     */
    public static void activityLog(Activity activity, String methodName, String logTag, boolean isLogE) {
        if (activity == null || activity.isFinishing() || activity.isDestroyed()) {
            return;
        }
        String finalLogTag = safeTAG(logTag);
        printLog(finalLogTag, activity, methodName, isLogE);
    }

}
