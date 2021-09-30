package com.lotteryviewer.home.util

import com.lotteryviewer.base.app.BaseApplication
import com.lotteryviewer.base.util.BaseSPUtil

/**
 * @Author: duke
 * @DateTime: 2021-03-24 17:52:50
 * @Description: 功能描述：
 *
 */
object HomeSPUtil {

    // 免责申明弹框是否需要弹出(每次启动应用时都可能会弹出，仅在同意后才不会再弹出)
    private const val KEY_DISCLAIMER_DIALOG_NEED_SHOW = "key_disclaimer_dialog_show"

    fun isNeedShowDisclaimerDialog(): Boolean {
        return BaseSPUtil.getBoolean(BaseApplication.get(), KEY_DISCLAIMER_DIALOG_NEED_SHOW, true)
    }

    fun setConsumeDisclaimerDialog() {
        BaseSPUtil.putData(BaseApplication.get(), KEY_DISCLAIMER_DIALOG_NEED_SHOW, false)
    }

}