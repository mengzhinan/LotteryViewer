package com.lotteryviewer.base.app

import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast

/**
 * @Author: duke
 * @DateTime: 2021-04-25 19:07:49
 * @Description: 功能描述：
 */
class CrashExceptionHandler private constructor() : Thread.UncaughtExceptionHandler {
    /**
     * 在 Application 开始时调用
     * 设置应用默认的全局捕获异常器
     */
    fun setDefaultUnCrashExceptionHandler() {
        Thread.setDefaultUncaughtExceptionHandler(this)
    }

    /**
     * 应用没有捕抓的异常会到这里来,
     * 如果我们设置了应用的默认全局捕抓异常为 CrashExceptionHandler 的话
     */
    override fun uncaughtException(thread: Thread?, throwable: Throwable?) {
        if (thread == null || throwable == null) {
            return
        }
        Log.e(TAG, "全局异常，thread = " + thread.name + ", error = " + throwable.message)
        Handler(Looper.getMainLooper()).post {
            Toast.makeText(
                BaseApplication.get(),
                "全局异常，error = " + throwable.message,
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    companion object {
        private val TAG = CrashExceptionHandler::class.java.simpleName
        private var crashExceptionHandler: CrashExceptionHandler? = null
        val instance: CrashExceptionHandler?
            get() {
                if (crashExceptionHandler == null) {
                    synchronized(CrashExceptionHandler::class.java) {
                        if (crashExceptionHandler == null) {
                            crashExceptionHandler = CrashExceptionHandler()
                        }
                    }
                }
                return crashExceptionHandler
            }
    }
}