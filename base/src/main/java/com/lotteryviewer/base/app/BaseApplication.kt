package com.lotteryviewer.base.app

import android.app.Application
import com.lotteryviewer.base.app.ActivityStack.init

/**
 * @Author: duke
 * @DateTime: 2021-03-17 10:53:57
 * @Description: 功能描述：
 */
object BaseApplication {

    private var mApplication: Application? = null

    private fun setApplication(application: Application) {
        mApplication = application
    }

    fun get(): Application? {
        if (mApplication != null) {
            return mApplication
        }
        throw NullPointerException("Application 为空")
    }

    fun init(application: Application) {
        setApplication(application)
        init(get())
        CrashExceptionHandler.instance?.setDefaultUnCrashExceptionHandler()
    }

}