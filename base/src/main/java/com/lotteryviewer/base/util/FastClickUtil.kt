package com.lotteryviewer.base.util

/**
 * @Author: duke
 * @DateTime: 2021-03-17 12:46:23
 * @Description: 功能描述：
 */
class FastClickUtil {

    private var lastClickTime: Long = 0

    init {
        resetTime()
    }

    fun resetTime() {
        lastClickTime = 0
    }

    fun isFastClick(timeThreshold: Long = 800): Boolean {
        val currentTime = System.currentTimeMillis()
        val differenceTime = currentTime - lastClickTime
        lastClickTime = currentTime
        return differenceTime <= timeThreshold
    }


}
