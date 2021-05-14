package com.lotteryviewer.base.util

import android.content.Context
import android.os.Build
import android.util.DisplayMetrics

/**
 * @Author: duke
 * @DateTime: 2021-03-17 12:26:47
 * @Description: 功能描述：
 */
object DisplayUtil {

    private fun getDisplayMetrics(context: Context?): DisplayMetrics? {
        context ?: return null
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val dm = DisplayMetrics()
            context.display?.getRealMetrics(dm)
            dm
        } else {
            context.resources.displayMetrics
        }
    }

    fun convertDpToPx(dp: Float, context: Context?): Int {
        val dm = getDisplayMetrics(context) ?: return 0
        return Math.ceil((dp * dm.density).toDouble()).toInt()
    }

    fun convertSpToPx(dp: Float, context: Context?): Int {
        val dm = getDisplayMetrics(context) ?: return 0
        return Math.ceil((dp * dm.scaledDensity).toDouble()).toInt()
    }

    fun getWidthPixels(context: Context?): Int {
        val dm = getDisplayMetrics(context) ?: return 0
        return dm.widthPixels
//        val d = context?.display ?: return 0
//        val rotation = d.rotation
//        return if (rotation == Surface.ROTATION_0 || rotation == Surface.ROTATION_180) {
//            dm.widthPixels
//        } else {
//            dm.heightPixels
//        }
    }

    fun getHeightPixels(context: Context?): Int {
        val dm = getDisplayMetrics(context) ?: return 0
        return dm.heightPixels
//        val d = context?.display ?: return 0
//        val rotation = d.rotation
//        return if (rotation == Surface.ROTATION_0 || rotation == Surface.ROTATION_180) {
//            dm.heightPixels
//        } else {
//            dm.widthPixels
//        }
    }

}