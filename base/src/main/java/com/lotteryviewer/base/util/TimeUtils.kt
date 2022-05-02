package com.lotteryviewer.base.util

import java.text.SimpleDateFormat
import java.util.*

/**
 * @Author: duke
 * @DateTime: 2021-12-10 15:49:14
 * @Description: 功能描述：
 *
 */
object TimeUtils {

    fun currentTimeToString(): String {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = System.currentTimeMillis()

        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH) + 1
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)
        val second = calendar.get(Calendar.SECOND)

        val monthStr = if (month < 10) "0$month" else "$month"
        val dayStr = if (day < 10) "0$day" else "$day"
        val hourStr = if (hour < 10) "0$hour" else "$hour"
        val minuteStr = if (minute < 10) "0$minute" else "$minute"
        val secondStr = if (second < 10) "0$second" else "$second"
        return "$year-$monthStr-$dayStr $hourStr:$minuteStr:$secondStr"
    }

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

    fun getDateTimeStr(): String {
        val sdf = SimpleDateFormat("yyyy.MM.dd HH:mm:ss")
        return sdf.format(Date())
    }

}
