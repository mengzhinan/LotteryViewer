package com.lotteryviewer.base;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import androidx.annotation.Nullable;

import java.util.ArrayList;

/**
 * @Author: duke
 * @DateTime: 2021-03-17 11:35:26
 * @Description: 功能描述：
 */
public class ActivityStack {

    private static final ArrayList<Activity> ACTIVITIES = new ArrayList<>();
    private static int activeCount = 0;

    public static void init(Application application) {
        application.registerActivityLifecycleCallbacks(new SimpleActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                ACTIVITIES.add(activity);
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                ACTIVITIES.remove(activity);
            }

            @Override
            public void onActivityStarted(Activity activity) {
                activeCount++;
            }

            @Override
            public void onActivityStopped(Activity activity) {
                // 如果使用 onActivityPaused，有可能会导致 activeCount==0
                // 因为开新 Activity 的时候，设 A 打开 B，顺序 A.onPause -> B.onCreate -> B.onResume -> A.onStop
                // 这会导致存在一个短暂的 activeCount 为 0 的情况

                // 就是如果 B 是一个透明 Activity，A 不会 onPause，这样 activeCount 会大于 1，
                // 同时如果下拉通知栏，也不会认为 App 处于后台，因为这只会触发 onPause。
                // 但是应该无伤大雅
                activeCount--;
            }
        });
    }

    public static int getActivitiesCount() {
        return ACTIVITIES.size();
    }

    public static ArrayList<Activity> getActivities() {
        // 返回 new list
        return new ArrayList<>(ACTIVITIES);
    }

    /**
     * 返回顶部 Activity。如果此 Activity 正处于 finish 过程中，那么返回下一个
     */
    @Nullable
    public static Activity getTopActivity() {
        for (int i = ACTIVITIES.size() - 1; i >= 0; i--) {
            Activity activity = ACTIVITIES.get(i);
            if (!activity.isFinishing()) {
                return activity;
            }
        }
        return null;
    }

    /**
     * 当前 Application 是否处于前台
     */
    public static boolean isForeground() {
        return activeCount > 0;
    }

}
