package com.lotteryviewer.base.util

/**
 * @Author: duke
 * @DateTime: 2021-03-17 12:46:23
 * @Description: 功能描述：
 */
object FastClickUtil {

    private var LAST_CLICK_TIME: Long = 0

    val isFastClick: Boolean
        get() = isFastClick(800)

    fun isFastClick(timeThreshold: Long): Boolean {
        val currentTime = System.currentTimeMillis()
        val differenceTime = currentTime - LAST_CLICK_TIME
        LAST_CLICK_TIME = currentTime
        return differenceTime <= timeThreshold
    }

}
