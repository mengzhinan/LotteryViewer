package com.lotteryviewer.base.app

import android.app.Activity
import android.app.Application.ActivityLifecycleCallbacks
import android.os.Bundle

/**
 * @Author: duke
 * @DateTime: 2021-03-17 11:13:45
 * @Description: 功能描述：
 */
open class SimpleActivityLifecycleCallbacks : ActivityLifecycleCallbacks {
    /**
     * Called as the first step of the Activity being created. This is always called before
     * [Activity.onCreate].
     */
    override fun onActivityPreCreated(activity: Activity, savedInstanceState: Bundle?) {}

    /**
     * Called when the Activity calls [super.onCreate()][Activity.onCreate].
     */
    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {}

    /**
     * Called as the last step of the Activity being created. This is always called after
     * [Activity.onCreate].
     */
    override fun onActivityPostCreated(activity: Activity, savedInstanceState: Bundle?) {}

    /**
     * Called as the first step of the Activity being started. This is always called before
     * [Activity.onStart].
     */
    override fun onActivityPreStarted(activity: Activity) {}

    /**
     * Called when the Activity calls [super.onStart()][Activity.onStart].
     */
    override fun onActivityStarted(activity: Activity) {}

    /**
     * Called as the last step of the Activity being started. This is always called after
     * [Activity.onStart].
     */
    override fun onActivityPostStarted(activity: Activity) {}

    /**
     * Called as the first step of the Activity being resumed. This is always called before
     * [Activity.onResume].
     */
    override fun onActivityPreResumed(activity: Activity) {}

    /**
     * Called when the Activity calls [super.onResume()][Activity.onResume].
     */
    override fun onActivityResumed(activity: Activity) {}

    /**
     * Called as the last step of the Activity being resumed. This is always called after
     * [Activity.onResume] and [Activity.onPostResume].
     */
    override fun onActivityPostResumed(activity: Activity) {}

    /**
     * Called as the first step of the Activity being paused. This is always called before
     * [Activity.onPause].
     */
    override fun onActivityPrePaused(activity: Activity) {}

    /**
     * Called when the Activity calls [super.onPause()][Activity.onPause].
     */
    override fun onActivityPaused(activity: Activity) {}

    /**
     * Called as the last step of the Activity being paused. This is always called after
     * [Activity.onPause].
     */
    override fun onActivityPostPaused(activity: Activity) {}

    /**
     * Called as the first step of the Activity being stopped. This is always called before
     * [Activity.onStop].
     */
    override fun onActivityPreStopped(activity: Activity) {}

    /**
     * Called when the Activity calls [super.onStop()][Activity.onStop].
     */
    override fun onActivityStopped(activity: Activity) {}

    /**
     * Called as the last step of the Activity being stopped. This is always called after
     * [Activity.onStop].
     */
    override fun onActivityPostStopped(activity: Activity) {}

    /**
     * Called as the first step of the Activity saving its instance state. This is always
     * called before [Activity.onSaveInstanceState].
     */
    override fun onActivityPreSaveInstanceState(activity: Activity, outState: Bundle) {}

    /**
     * Called when the Activity calls
     * [super.onSaveInstanceState()][Activity.onSaveInstanceState].
     */
    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {}

    /**
     * Called as the last step of the Activity saving its instance state. This is always
     * called after[Activity.onSaveInstanceState].
     */
    override fun onActivityPostSaveInstanceState(activity: Activity, outState: Bundle) {}

    /**
     * Called as the first step of the Activity being destroyed. This is always called before
     * [Activity.onDestroy].
     */
    override fun onActivityPreDestroyed(activity: Activity) {}

    /**
     * Called when the Activity calls [super.onDestroy()][Activity.onDestroy].
     */
    override fun onActivityDestroyed(activity: Activity) {}

    /**
     * Called as the last step of the Activity being destroyed. This is always called after
     * [Activity.onDestroy].
     */
    override fun onActivityPostDestroyed(activity: Activity) {}
}