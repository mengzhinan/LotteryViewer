package com.lotteryviewer.base.app;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;

/**
 * @Author: duke
 * @DateTime: 2021-03-17 11:13:45
 * @Description: 功能描述：
 */
public class SimpleActivityLifecycleCallbacks implements Application.ActivityLifecycleCallbacks {

    /**
     * Called as the first step of the Activity being created. This is always called before
     * {@link Activity#onCreate}.
     */
    @Override
    public void onActivityPreCreated(@NonNull Activity activity, @androidx.annotation.Nullable Bundle savedInstanceState) {

    }

    /**
     * Called when the Activity calls {@link Activity#onCreate super.onCreate()}.
     */
    @Override
    public void onActivityCreated(@NonNull Activity activity, @androidx.annotation.Nullable Bundle savedInstanceState) {

    }

    /**
     * Called as the last step of the Activity being created. This is always called after
     * {@link Activity#onCreate}.
     */
    @Override
    public void onActivityPostCreated(@NonNull Activity activity, @androidx.annotation.Nullable Bundle savedInstanceState) {

    }

    /**
     * Called as the first step of the Activity being started. This is always called before
     * {@link Activity#onStart}.
     */
    @Override
    public void onActivityPreStarted(@NonNull Activity activity) {

    }

    /**
     * Called when the Activity calls {@link Activity#onStart super.onStart()}.
     */
    @Override
    public void onActivityStarted(@NonNull Activity activity) {

    }

    /**
     * Called as the last step of the Activity being started. This is always called after
     * {@link Activity#onStart}.
     */
    @Override
    public void onActivityPostStarted(@NonNull Activity activity) {

    }

    /**
     * Called as the first step of the Activity being resumed. This is always called before
     * {@link Activity#onResume}.
     */
    @Override
    public void onActivityPreResumed(@NonNull Activity activity) {

    }

    /**
     * Called when the Activity calls {@link Activity#onResume super.onResume()}.
     */
    @Override
    public void onActivityResumed(@NonNull Activity activity) {

    }

    /**
     * Called as the last step of the Activity being resumed. This is always called after
     * {@link Activity#onResume} and {@link Activity#onPostResume}.
     */
    @Override
    public void onActivityPostResumed(@NonNull Activity activity) {

    }

    /**
     * Called as the first step of the Activity being paused. This is always called before
     * {@link Activity#onPause}.
     */
    @Override
    public void onActivityPrePaused(@NonNull Activity activity) {

    }

    /**
     * Called when the Activity calls {@link Activity#onPause super.onPause()}.
     */
    @Override
    public void onActivityPaused(@NonNull Activity activity) {

    }

    /**
     * Called as the last step of the Activity being paused. This is always called after
     * {@link Activity#onPause}.
     */
    @Override
    public void onActivityPostPaused(@NonNull Activity activity) {

    }

    /**
     * Called as the first step of the Activity being stopped. This is always called before
     * {@link Activity#onStop}.
     */
    @Override
    public void onActivityPreStopped(@NonNull Activity activity) {

    }

    /**
     * Called when the Activity calls {@link Activity#onStop super.onStop()}.
     */
    @Override
    public void onActivityStopped(@NonNull Activity activity) {

    }

    /**
     * Called as the last step of the Activity being stopped. This is always called after
     * {@link Activity#onStop}.
     */
    @Override
    public void onActivityPostStopped(@NonNull Activity activity) {

    }

    /**
     * Called as the first step of the Activity saving its instance state. This is always
     * called before {@link Activity#onSaveInstanceState}.
     */
    @Override
    public void onActivityPreSaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {

    }

    /**
     * Called when the Activity calls
     * {@link Activity#onSaveInstanceState super.onSaveInstanceState()}.
     */
    @Override
    public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {

    }

    /**
     * Called as the last step of the Activity saving its instance state. This is always
     * called after{@link Activity#onSaveInstanceState}.
     */
    @Override
    public void onActivityPostSaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {

    }

    /**
     * Called as the first step of the Activity being destroyed. This is always called before
     * {@link Activity#onDestroy}.
     */
    @Override
    public void onActivityPreDestroyed(@NonNull Activity activity) {

    }

    /**
     * Called when the Activity calls {@link Activity#onDestroy super.onDestroy()}.
     */
    @Override
    public void onActivityDestroyed(@NonNull Activity activity) {

    }

    /**
     * Called as the last step of the Activity being destroyed. This is always called after
     * {@link Activity#onDestroy}.
     */
    @Override
    public void onActivityPostDestroyed(@NonNull Activity activity) {

    }

}
