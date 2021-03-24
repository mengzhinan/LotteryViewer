package com.lotteryviewer.base.util

import android.util.Log

/**
 * @Author: duke
 * @DateTime: 2021-03-24 19:27:00
 * @Description: 功能描述：
 *
 */
object LVLogger {

    const val TAG = "test_lv_log"
    var isDebug = true

    fun logD(s: String?) {
        logD(TAG, s)
    }

    fun logD(tag: String?, s: String?) {
        if (!isDebug) {
            return
        }
        s ?: return
        val finalTag = tag ?: TAG
        Log.d(finalTag, s)
    }

    fun logE(s: String?) {
        logE(TAG, s)
    }

    fun logE(tag: String?, s: String?) {
        if (!isDebug) {
            return
        }
        s ?: return
        val finalTag = tag ?: TAG
        Log.e(finalTag, s)
    }

}