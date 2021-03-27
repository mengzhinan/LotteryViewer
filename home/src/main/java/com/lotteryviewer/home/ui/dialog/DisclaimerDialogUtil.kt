package com.lotteryviewer.home.ui.dialog

import android.app.Activity
import androidx.appcompat.app.AlertDialog
import com.lotteryviewer.base.interfaces.FunctionNone
import com.lotteryviewer.base.util.DisplayUtil
import com.lotteryviewer.home.R
import com.lotteryviewer.home.util.HomeSPUtil
import kotlin.system.exitProcess

/**
 * @Author: duke
 * @DateTime: 2021-03-24 17:51:38
 * @Description: 功能描述：
 *
 */
object DisclaimerDialogUtil {

    /**
     * 显示隐私协议弹框
     * @param activity context
     * @param agreeCallBack 点击同意的回调
     * @return 是否成功显示弹框
     */
    fun showDisclaimerDialog(activity: Activity?, agreeCallBack: FunctionNone?): Boolean {
        activity ?: return false

        if (!HomeSPUtil.isNeedShowDisclaimerDialog()) {
            return false
        }

        val dialog = AlertDialog.Builder(activity)
            .setMessage(R.string.about_app_content)
            .setNegativeButton("退出") { _, _ ->
                activity.finishAffinity()
                exitProcess(0)
            }.setPositiveButton(
                "同意并继续"
            ) { _, _ -> // go on use app
                HomeSPUtil.setConsumeDisclaimerDialog()
                agreeCallBack?.onCallBack()
            }.setCancelable(false)
            .setTitle(R.string.about_app_disclaimer_title)
            .show()

        try {

            val screenWidth = DisplayUtil.getWidthPixels(activity)
            val screenHeight = DisplayUtil.getHeightPixels(activity)
            dialog.window?.setLayout(screenWidth * 9 / 10, screenHeight * 3 / 5)

        } catch (e: Exception) {
            e.printStackTrace()
        }

        return true
    }

}