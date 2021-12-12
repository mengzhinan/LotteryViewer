package com.lotteryviewer.base.util

import java.util.*

/**
 * @Author: duke
 * @DateTime: 2021-12-10 15:49:14
 * @Description: 功能描述：
 *
 */
object TimeUtils {

    /**
     * 获取某个时间的总毫秒数
     * tip:
     * monthStartZero 月份参数从 0 开始
     * hourOfDay 小时参数是 24 小时制
     */
    fun getTimeInMillis(
        year: Int,
        monthStartZero: Int,
        dayOfMonth: Int,
        hourOfDay: Int,
        minute: Int,
        second: Int
    ): Long {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, monthStartZero)
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
        calendar.set(Calendar.MINUTE, minute)
        calendar.set(Calendar.SECOND, second)
        return calendar.timeInMillis
    }

    /**
     * 比较当前时间是否在给定时间区间内
     */
    fun isTimeMillisCurrentBetween(
        timeMillisStart: Long,
        timeMillisEnd: Long
    ): Boolean {
        return isTimeMillisBetween(
            System.currentTimeMillis(),
            timeMillisStart,
            timeMillisEnd
        )
    }

    /**
     * 比较某个时间是否在给定时间区间内
     */
    fun isTimeMillisBetween(
        timeMillisCurrent: Long,
        timeMillisStart: Long,
        timeMillisEnd: Long
    ): Boolean {
        return timeMillisCurrent in timeMillisStart..timeMillisEnd
    }

}
