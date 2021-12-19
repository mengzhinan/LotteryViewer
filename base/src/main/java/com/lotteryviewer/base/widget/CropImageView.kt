package com.lotteryviewer.base.widget

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Matrix
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView

/**
 * @ Author: duke
 * @ DateTime: 2021-12-07 16:14:00
 * @ Description: 功能描述： 支持特殊裁切的 ImageView
 */
class CropImageView : AppCompatImageView {
    constructor(context: Context) : super(context) {}
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {}
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
    }

    /**
     * 设置 scaleType：使左右上平铺，底部多余的裁掉
     */
    fun postCropBottom() {
        post {
            val drawable = drawable ?: return@post
            var bitmap = drawableToBitmap(drawable)
            bitmap = cropBottom(bitmap)
            if (bitmap == null || bitmap.isRecycled) {
                return@post
            }
            setImageBitmap(bitmap)
            invalidate()
        }
    }

    /**
     * 图片宽度拉伸到 ImageView 宽度
     * 图片顶部与 ImageView 顶部对齐，底部超出 ImageView 的多余部分裁掉
     *
     * @param bitmap 原始图片
     * @return new bitmap
     */
    private fun cropBottom(bitmap: Bitmap?): Bitmap? {
        if (bitmap == null || bitmap.isRecycled) {
            return null
        }
        val ivWidth = measuredWidth
        val ivHeight = measuredHeight
        val bitmapWidth = bitmap.width
        val bitmapHeight = bitmap.height
        val scaleWidth = ivWidth.toFloat() / bitmapWidth
        val matrix = Matrix()
        matrix.postScale(scaleWidth, scaleWidth)

        // 裁掉 bitmap 的底部多余部分
        val targetHeight = Math.min((ivHeight / scaleWidth).toInt(), bitmapHeight)
        return Bitmap.createBitmap(bitmap, 0, 0, bitmapWidth, targetHeight, matrix, true)
    }

    private fun drawableToBitmap(drawable: Drawable?): Bitmap? {
        if (drawable == null) {
            return null
        }
        val drawableWidth = drawable.intrinsicWidth
        val drawableHeight = drawable.intrinsicHeight
        drawable.setBounds(0, 0, drawableWidth, drawableHeight)
        val config = Bitmap.Config.RGB_565
        val bitmap = Bitmap.createBitmap(drawableWidth, drawableHeight, config)
        val canvas = Canvas(bitmap)
        drawable.draw(canvas)
        return bitmap
    }
}