package com.lotteryviewer.base.util

import android.content.Context
import android.util.DisplayMetrics
import android.view.Surface

/**
 * @Author: duke
 * @DateTime: 2021-03-17 12:26:47
 * @Description: 功能描述：
 */
object DisplayUtil {

    private fun getRealMetrics(context: Context?): DisplayMetrics? {
        val display = context?.display ?: return null
        val dm = DisplayMetrics()
        display.getRealMetrics(dm)
        return dm
    }

    fun convertDpToPx(dp: Float, context: Context?): Int {
        val dm = getRealMetrics(context) ?: return 0
        return Math.ceil((dp * dm.density).toDouble()).toInt()
    }

    fun getWidthPixels(context: Context?): Int {
        val dm = getRealMetrics(context) ?: return 0
        val d = context?.display ?: return 0
        val rotation = d.rotation
        return if (rotation == Surface.ROTATION_0 || rotation == Surface.ROTATION_180) {
            dm.widthPixels
        } else {
            dm.heightPixels
        }
    }

    fun getHeightPixels(context: Context?): Int {
        val dm = getRealMetrics(context) ?: return 0
        val d = context?.display ?: return 0
        val rotation = d.rotation
        return if (rotation == Surface.ROTATION_0 || rotation == Surface.ROTATION_180) {
            dm.heightPixels
        } else {
            dm.widthPixels
        }
    }

}