package com.lotteryviewer.base.util

import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.text.TextUtils
import androidx.annotation.IntRange
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream


/**
 * @Author: duke
 * @DateTime: 2021-04-02 17:25:37
 * @Description: 功能描述：
 *
 */
object BitmapUtil {

    fun saveBitmapToFile(
        context: Context?,
        bitmap: Bitmap?,
        fileName: String?
    ) {
        if (context == null
            || bitmap == null
            || fileName == null
            || TextUtils.isEmpty(fileName.trim())
        ) {
            return
        }

        var bo: ByteArrayOutputStream? = ByteArrayOutputStream()
        var fs: FileOutputStream? = null

        try {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bo)
            val file = File(fileName)
            if (!file.exists()) {
                file.parentFile?.mkdirs()
                file.createNewFile()
            }

            fs = FileOutputStream(file)
            fs.write(bo?.toByteArray())

        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            try {
                fs?.flush()
                fs?.close()
                bo?.flush()
                bo?.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
            bo = null
            fs = null
        }
    }

    fun rotateBitmap(bitmap: Bitmap?, angle: Int): Bitmap? {
        if (null == bitmap || angle == 0) {
            return bitmap
        }
        val matrix = Matrix()
        matrix.postRotate(angle.toFloat())
        return Bitmap.createBitmap(
            bitmap, 0, 0,
            bitmap.width, bitmap.height,
            matrix, true
        )
    }

    private fun isEmptyBitmap(src: Bitmap?): Boolean {
        return src == null || src.width == 0 || src.height == 0
    }

    fun compressByQuality(
        srcBitmap: Bitmap,
        @IntRange(from = 0, to = 100) quality: Int,
        isRecycleSrc: Boolean = false
    ): Bitmap? {
        if (isEmptyBitmap(srcBitmap)) return null
        val baos = ByteArrayOutputStream()
        srcBitmap.compress(Bitmap.CompressFormat.JPEG, quality, baos)
        val bytes = baos.toByteArray()
        if (isRecycleSrc && !srcBitmap.isRecycled) srcBitmap.recycle()
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
    }

    fun compressByByteSize(
        srcBitmap: Bitmap,
        maxByteSize: Long,
        isRecycleSrc: Boolean = false
    ): Bitmap? {
        var quality = 100
        val baos = ByteArrayOutputStream()
        srcBitmap.compress(Bitmap.CompressFormat.JPEG, quality, baos)
        while (baos.toByteArray().size > maxByteSize) {
            baos.reset()
            srcBitmap.compress(Bitmap.CompressFormat.JPEG, quality, baos)
            quality -= 10
            if (quality <= 0) {
                break
            }
        }
        val bytes = baos.toByteArray()
        if (isRecycleSrc && !srcBitmap.isRecycled) srcBitmap.recycle()
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
    }

    fun resizeBitmapFromPath(
        filePath: String,
        reqWidth: Int,
        reqHeight: Int
    ): Bitmap? {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeFile(filePath, options)
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight)
        options.inJustDecodeBounds = false
        return BitmapFactory.decodeFile(filePath, options)
    }

    fun resizeBitmapFromResource(
        res: Resources?,
        resId: Int,
        reqWidth: Int,
        reqHeight: Int
    ): Bitmap? {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeResource(res, resId, options)
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight)
        options.inJustDecodeBounds = false
        return BitmapFactory.decodeResource(res, resId, options)
    }

    private fun calculateInSampleSize(
        options: BitmapFactory.Options,
        reqWidth: Int,
        reqHeight: Int
    ): Int {
        var inSampleSize = 1
        if (options.outWidth > reqWidth || options.outHeight > reqHeight) {
            val widthRatio = Math.round(options.outWidth.toFloat() / reqWidth.toFloat())
            val heightRatio = Math.round(options.outHeight.toFloat() / reqHeight.toFloat())
            inSampleSize = Math.max(widthRatio, heightRatio)
        }
        return inSampleSize
    }

}