package com.lotteryviewer.android

import android.content.Context
import androidx.multidex.MultiDex
import com.lotteryviewer.base.app.AppBuildConfig
import com.lotteryviewer.base.app.BaseApplication
import com.lotteryviewer.home.ui.activities.MainActivity

/**
 * @Author: duke
 * @DateTime: 2021-03-17 15:07:57
 * @Description: 功能描述：
 */
class LotteryApplication : BaseApplication() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)

        AppBuildConfig.newBuilder()
            .DEBUG(BuildConfig.DEBUG)
            .APPLICATION_ID(BuildConfig.APPLICATION_ID)
            .MAIN_ACTIVITY_NAME(MainActivity::class.java.name)
            .BUILD_TYPE(BuildConfig.BUILD_TYPE)
            .FLAVOR(BuildConfig.FLAVOR)
            .VERSION_CODE(BuildConfig.VERSION_CODE)
            .VERSION_NAME(BuildConfig.VERSION_NAME)


    }

}
