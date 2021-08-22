package com.lotteryviewer.base.util

import android.app.Activity

/**
 * @Author: duke
 * @DateTime: 2021-04-08 19:59:20
 * @Description: 功能描述：
 *
 */
object ActivityUtil {

    /**
     * activity 是否有效，未被销毁
     * @return true 有效
     */
    fun isActivityValid(activity: Activity?): Boolean {
        return activity != null && !activity.isFinishing && !activity.isDestroyed
    }

}
