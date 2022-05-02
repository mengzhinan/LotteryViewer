package com.lotteryviewer.base.util

import android.graphics.Color
import androidx.annotation.ColorInt
import androidx.annotation.IntRange
import java.util.*

/**
 * @Author: duke
 * @DateTime: 2021-04-06 09:54:58
 * @Description: 功能描述：颜色值百分比对应的透明度计算工具类
 * 100 - FF - 255
 * 0   - 00 - 00
 */
object ColorUtil {

    /**
     * 通过百分比计算出对应颜色值的 16 进制值
     */
    fun getPercentHexValue(@IntRange(from = 0, to = 100) percent: Int): String {
        var v = (255 * percent / 100).toString(16).toUpperCase(Locale.getDefault())
        if (v.length == 1) {
            v = "0$v"
        }
        return v
    }

    fun parseColor(color: String?): Int {
        if (color.isNullOrBlank()) {
            return 0
        }
        return try {
            val colorNew = color.trim()
            Color.parseColor(
                if (!colorNew.startsWith("#")) {
                    "#${colorNew}"
                } else {
                    colorNew
                }
            )
        } catch (e: Exception) {
            0
        }
    }

    fun getColorWithAlpha(
        @IntRange(from = 0, to = 100) intAlpha: Int,
        @ColorInt colorInt: Int
    ): Int {
        if (colorInt == 0) {
            return 0
        }
        val alpha = Math.round(intAlpha * 255 / 100.0f)
        val red = Color.red(colorInt)
        val green = Color.green(colorInt)
        val blue = Color.blue(colorInt)
        return Color.argb(alpha, red, green, blue)
    }

    fun getColorWithAlpha(@IntRange(from = 0, to = 100) intAlpha: Int, colorStr: String): Int {
        return getColorWithAlpha(intAlpha, parseColor(colorStr))
    }

}