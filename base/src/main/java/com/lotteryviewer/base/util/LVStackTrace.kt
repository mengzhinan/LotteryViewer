package com.lotteryviewer.base.util

import android.util.Log

/**
 * author: duke
 * version: 1.0
 * dateTime: 2021-06-04 21:47
 * description:
 *
 */
object LVStackTrace {

    /**
     * 获取当前调用栈
     */
    fun getStackTrace(): String {
        return Log.getStackTraceString(Throwable())
    }

    /**
     * 输出 Java 堆栈信息
     */
    fun getMethodStack(): String {
        val stackTraceElementArray = Thread.currentThread().stackTrace ?: return ""
        val sb = StringBuilder()
        for (item in stackTraceElementArray) {
            val t = item?.toString()?.trim() ?: ""
            if ("" == t) {
                continue
            }
            sb.append(t).append("\n")
        }
        return sb.toString()
    }

}