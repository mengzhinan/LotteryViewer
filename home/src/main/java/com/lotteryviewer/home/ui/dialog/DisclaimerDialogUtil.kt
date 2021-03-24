package com.lotteryviewer.home.ui.dialog

import android.app.Activity
import android.content.DialogInterface
import android.util.DisplayMetrics
import androidx.appcompat.app.AlertDialog
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

    fun showDisclaimerDialog(activity: Activity?) {
        activity ?: return

        if (!HomeSPUtil.isNeedShowDisclaimerDialog()) {
            return
        }

        val dialog = AlertDialog.Builder(activity)
            .setMessage(R.string.about_app_content)
            .setNegativeButton("退出", object : DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface?, which: Int) {
                    activity.finishAffinity()
                    exitProcess(0)
                }
            }).setPositiveButton("同意并继续", object : DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface?, which: Int) {
                    // go on use app
                    HomeSPUtil.setConsumeDisclaimerDialog()
                }
            }).setCancelable(false)
            .setTitle(R.string.about_app_disclaimer_title)
            .show()

        try {
            val m = DisplayMetrics()
            activity.display?.getRealMetrics(m)
            val screenWidth = m.widthPixels
            val screenHeight = m.heightPixels
            dialog.window?.setLayout(screenWidth * 9 / 10, screenHeight * 3 / 5)

        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

}