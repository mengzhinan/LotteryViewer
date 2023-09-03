package com.lotteryviewer.base.util

import android.graphics.Color
import androidx.annotation.ColorInt
import androidx.annotation.FloatRange
import androidx.annotation.IntRange
import java.util.Locale

/**
 * @Author: duke
 * @DateTime: 2023-08-10 17:36:42
 * @Description: 功能描述：颜色值转换工具类
 */
object ColorUtil {

    /**
     * 获取 Color 颜色值 16 进制的字符串表示形式。
     * 如：(int) 红色 -> #FF0000
     *
     * @param color 颜色值 int。
     * @param isWithAlpha 是否需要拼接 alpha 不透明度部分。如：需要拼接，那么颜色值就是 #FF000000 中的 FF 部分。
     * @return 颜色值 16 进制的字符串。如：#FF0000。
     */
    @JvmStatic
    fun getColorToHexString(@ColorInt color: Int, isWithAlpha: Boolean? = true): String {
        val alphaHexStr = intToHexString(Color.alpha(color))
        val redHexStr = intToHexString(Color.red(color))
        val greenHexStr = intToHexString(Color.green(color))
        val blueHexStr = intToHexString(Color.blue(color))
        return if (isWithAlpha == true) {
            "#${alphaHexStr}${redHexStr}${greenHexStr}${blueHexStr}"
        } else {
            "#${redHexStr}${greenHexStr}${blueHexStr}"
        }
    }

    /**
     * 获取 Color 颜色值的 int 表示形式。
     * 如：#FF0000 -> (int) 红色
     *
     * @param color 颜色 16 进制的字符串。如：#FF0000。
     * @return 颜色值 int。如果返回值为 null，则表示解析失败。
     */
    @JvmStatic
    fun getColorToIntSafety(color: String?): Int {
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
        } catch (e: IllegalArgumentException) {
            0
        }
    }

    /**
     * 设置颜色的透明度，并获取设置后的颜色值 int。
     * 如：(int) #FF0000，50 -> (int) 7FFF0000
     *
     * @param color      原始颜色值 int。
     * @param alphaFloat 透明度。0.0: 完全透明；1.0: 完全不透明。
     * @return 新的颜色值
     */
    @JvmStatic
    fun getColorIntByAlpha(
        @ColorInt color: Int, @FloatRange(from = 0.0, to = 1.0) alphaFloat: Float
    ): Int {
        val newAlphaFloat = if (alphaFloat < 0.0f) {
            0.0f
        } else if (alphaFloat > 1.0f) {
            1.0f
        } else {
            alphaFloat
        }
        // Color.alpha 取值范围：@IntRange(from = 0, to = 255)
        // 所以要把外部传入的值 alphaFloat 从 [0.0, 1.0] 转换到范围为 [0, 255] 的值
        val alphaForColor = Math.round(newAlphaFloat * 255)
        val red = Color.red(color)
        val green = Color.green(color)
        val blue = Color.blue(color)
        // 覆盖原始 color 的 alpha 值，重新合成新的 color 值
        return Color.argb(alphaForColor, red, green, blue)
    }

    /**
     * 设置颜色的透明度，并获取设置后的颜色值 int。
     * 如：(int) #FF0000，50 -> (int) 7FFF0000
     *
     * @param color    原始颜色值 int。
     * @param alphaInt 透明度。0: 完全透明；100: 完全不透明。
     * @return 新的颜色值
     */
    @JvmStatic
    fun getColorIntByAlpha(
        @ColorInt color: Int, @IntRange(from = 0, to = 100) alphaInt: Int
    ): Int {
        // 转换为小数值 @FloatRange(from = 0.0, to = 1.0)
        val alphaFloat = alphaInt * 1.0f / 100
        return getColorIntByAlpha(color, alphaFloat)
    }


    /**
     * 获取颜色的透明度值 float
     *
     * @param color 原始颜色值 int。
     * @return 透明度值 float。
     * 取值范围 [0.0, 1.0]。
     * 0.0: 完全透明；1.0: 完全不透明。
     */
    @JvmStatic
    fun getAlphaFloat(@ColorInt color: Int): Float {
        // Color.alpha 取值范围：@IntRange(from = 0, to = 255)
        val alphaForColor = Color.alpha(color)
        // 转换为常人可以理解的范围 [0.0, 1.0]
        val alphaFloat = alphaForColor * 1.0f / 255
        // 保留两位小数
        return Math.round(alphaFloat * 100) * 1.0f / 100
    }

    /**
     * 获取颜色的透明度值 string
     *
     * @param color 原始颜色值 int。
     * @return 透明度值 16 进制字符串。取值范围：["00", "FF"]
     */
    @JvmStatic
    fun getAlphaHexString(@ColorInt color: Int): String {
        // Color.alpha 取值范围：@IntRange(from = 0, to = 255)
        val alphaForColor = Color.alpha(color)
        return intToHexString(alphaForColor)
    }

    /**
     * int 转换为 16 进制字符串。
     * 如：255 -> FF
     */
    private fun intToHexString(@IntRange(from = 0, to = 255) intNumber: Int?): String {
        val v = if (intNumber == null || intNumber < 0) {
            0
        } else if (intNumber > 255) {
            255
        } else {
            intNumber
        }
        var str = v.toString(16).uppercase(Locale.ROOT)
        if (str.trim().length == 1) {
            str = "0$str"
        }
        return str
    }
}