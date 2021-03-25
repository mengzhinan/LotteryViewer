package com.lotteryviewer.base.app

import android.app.Application
import com.lotteryviewer.base.app.ActivityStack.init

/**
 * @Author: duke
 * @DateTime: 2021-03-17 10:53:57
 * @Description: 功能描述：
 */
open class BaseApplication : Application() {

    init {
        setApplication(this)
    }

    override fun onCreate() {
        super.onCreate()
        init(get())
    }

    companion object {
        private var REAL_INSTANCE: BaseApplication? = null
        private var sApplicationContext: Application? = null
        private fun setApplication(application: Application) {
            sApplicationContext = application
            if (application is BaseApplication) {
                REAL_INSTANCE = application
            }
        }

        fun get(): Application? {
            if (REAL_INSTANCE != null) {
                return REAL_INSTANCE
            }
            if (sApplicationContext != null) {
                return sApplicationContext
            }
            throw NullPointerException("Application 为空")
        }
    }

}