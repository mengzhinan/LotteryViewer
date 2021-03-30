package com.lotteryviewer.sunset.ui.dialog

import android.annotation.SuppressLint
import android.app.Activity
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import com.lotteryviewer.base.app.BaseApplication
import com.lotteryviewer.base.util.DisplayUtil
import com.lotteryviewer.sunset.R
import com.lotteryviewer.sunset.util.SunsetDataUtil

/**
 * @Author: duke
 * @DateTime: 2021-03-24 17:51:38
 * @Description: 功能描述：
 *
 */
object SunsetTodayDialogUtil {

    /**
     * 插件今天的日出日落信息
     * @param activity context
     */
    fun showTodayDialog(activity: Activity?) {
        activity ?: return

        val dialog = AlertDialog.Builder(activity)
            .setView(getView())
            .setPositiveButton("关闭", object : DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface?, which: Int) {
                    // do nothing
                }
            })
            .setCancelable(false)
            .setTitle(R.string.sunset_today_info)
            .show()

        try {

            val screenWidth = DisplayUtil.getWidthPixels(activity)
            val screenHeight = DisplayUtil.getHeightPixels(activity)
//            dialog.window?.setLayout(screenWidth * 9 / 10, screenHeight * 3 / 5)

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    @SuppressLint("InflateParams", "SetTextI18n")
    private fun getView(): View {
        val root = LayoutInflater.from(BaseApplication.get()).inflate(R.layout.sunset_dialog, null)

        val tvDate = root.findViewById<TextView>(R.id.date_text)
        val tvSunrise = root.findViewById<TextView>(R.id.sunrise_text)
        val tvSunMiddle = root.findViewById<TextView>(R.id.sun_middle_text)
        val tvSunset = root.findViewById<TextView>(R.id.sunset_text)
        val tvDayLength = root.findViewById<TextView>(R.id.day_length_text)
        val tvDayLight = root.findViewById<TextView>(R.id.day_light_text)
        val tvDayDark = root.findViewById<TextView>(R.id.day_dark_text)

        tvDate.text = "${getString(R.string.sunset_date)}${SunsetDataUtil.getDateStr()}"
        tvSunrise.text = "${getString(R.string.sunset_sunrise)}${SunsetDataUtil.getSunriseStr()}"
        tvSunMiddle.text = "${getString(R.string.sunset_middle)}${SunsetDataUtil.getSunMiddleStr()}"
        tvSunset.text = "${getString(R.string.sunset_sunset)}${SunsetDataUtil.getSunsetStr()}"
        tvDayLength.text =
            "${getString(R.string.sunset_day_length)}${SunsetDataUtil.getDayLengthStr()}"
        tvDayLight.text =
            "${getString(R.string.sunset_day_light)}${SunsetDataUtil.getDayLightStr()}"
        tvDayDark.text = "${getString(R.string.sunset_day_dark)}${SunsetDataUtil.getDayDarkStr()}"

        return root
    }

    private fun getString(@StringRes sid: Int): String {
        return BaseApplication.get()?.getString(sid) ?: ""
    }

}