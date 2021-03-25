package com.lotteryviewer.base.app

import android.app.Activity
import android.app.Application
import android.os.Bundle
import java.util.*

/**
 * @Author: duke
 * @DateTime: 2021-03-17 11:35:26
 * @Description: 功能描述：
 */
object ActivityStack {

    private val ACTIVITIES = ArrayList<Activity>()
    private var activeCount = 0

    fun init(application: Application?) {
        application?.registerActivityLifecycleCallbacks(object : SimpleActivityLifecycleCallbacks() {
            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                ACTIVITIES.add(activity)
            }

            override fun onActivityDestroyed(activity: Activity) {
                ACTIVITIES.remove(activity)
            }

            override fun onActivityStarted(activity: Activity) {
                activeCount++
            }

            override fun onActivityStopped(activity: Activity) {
                // 如果使用 onActivityPaused，有可能会导致 activeCount==0
                // 因为开新 Activity 的时候，设 A 打开 B，顺序 A.onPause -> B.onCreate -> B.onResume -> A.onStop
                // 这会导致存在一个短暂的 activeCount 为 0 的情况

                // 就是如果 B 是一个透明 Activity，A 不会 onPause，这样 activeCount 会大于 1，
                // 同时如果下拉通知栏，也不会认为 App 处于后台，因为这只会触发 onPause。
                // 但是应该无伤大雅
                activeCount--
            }
        })
    }

    val activitiesCount: Int
        get() = ACTIVITIES.size

    // 返回 new list
    val activities: ArrayList<Activity>
        get() = ArrayList(ACTIVITIES)

    /**
     * 返回顶部 Activity。如果此 Activity 正处于 finish 过程中，那么返回下一个
     */
    val topActivity: Activity?
        get() {
            for (i in ACTIVITIES.indices.reversed()) {
                val activity = ACTIVITIES[i]
                if (!activity.isFinishing) {
                    return activity
                }
            }
            return null
        }

    /**
     * 当前 Application 是否处于前台
     */
    val isForeground: Boolean
        get() = activeCount > 0
}