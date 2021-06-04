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
    fun getCurrentStackTrace(): String {
        return Log.getStackTraceString(Throwable())
    }

}