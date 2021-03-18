package com.lotteryviewer.android

import android.content.Context
import androidx.multidex.MultiDex
import com.lotteryviewer.base.app.BaseApplication

/**
 * @Author: duke
 * @DateTime: 2021-03-17 15:07:57
 * @Description: 功能描述：
 */
class LotteryApplication : BaseApplication() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

}
