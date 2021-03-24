package com.lotteryviewer.base.util

import android.graphics.drawable.GradientDrawable

/**
 * @Author: duke
 * @DateTime: 2021-03-24 17:10:06
 * @Description: 功能描述：
 *
 */
object GradientDrawableUtil {

    fun getRoundDrawable(radius: Int, bgColorInt: Int): GradientDrawable {
        val gradientDrawable = GradientDrawable()
        gradientDrawable.setColor(bgColorInt)
        gradientDrawable.cornerRadius = radius.toFloat()
        return gradientDrawable
    }

}
