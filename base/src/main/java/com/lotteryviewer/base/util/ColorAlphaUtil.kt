package com.lotteryviewer.base.util

import androidx.annotation.IntRange
import java.util.*

/**
 * @Author: duke
 * @DateTime: 2021-04-06 09:54:58
 * @Description: 功能描述：颜色值百分比对应的透明度计算工具类
 * 100 - FF - 255
 * 0   - 00 - 00
 */
object ColorAlphaUtil {


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

}