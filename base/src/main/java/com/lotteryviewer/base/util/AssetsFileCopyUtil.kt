package com.lotteryviewer.base.util

import android.content.Context
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream

/**
 * @Author: duke
 * @DateTime: 2021-03-22 18:02:29
 * @Description: 功能描述：
 *
 */
object AssetsFileCopyUtil {

    private fun copyAssetsFileToExtendedStorage(
        context: Context?,
        fullNameAndSuffix: String?,
        outFile: File?,
    ): Boolean {
        context ?: return false
        fullNameAndSuffix ?: return false
        outFile ?: return false

        if (outFile.exists()) {
            return true
        }

        var inputStream: InputStream? = null
        var fos: FileOutputStream? = null
        try {
            inputStream = context.assets.open(fullNameAndSuffix)
            fos = FileOutputStream(outFile)
            val buffer = ByteArray(1024)
            var byteCount = inputStream.read(buffer)
            while (byteCount != -1) {
                fos.write(buffer, 0, byteCount)
                byteCount = inputStream.read(buffer)
            }
            return true
        } catch (e: IOException) {
            e.printStackTrace()
            return false
        } finally {
            fos?.flush()
            fos?.close()
            inputStream?.close()
        }

    }

}