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

    /**
     * copy Assets 中的文件 to
     * @param context context
     * @param fullNameAndSuffix Assets 中的文件名 + 后缀名
     * @param isFocus 如果目标文件已经存在，是否强制覆盖旧文件
     * @return is copy success
     */
    fun copyAssetsFileTo(
        context: Context?,
        fullNameAndSuffix: String?,
        outFile: File?,
        isFocus: Boolean = false
    ): Boolean {
        context ?: return false
        fullNameAndSuffix ?: return false
        outFile ?: return false

        if (outFile.exists()) {
            if (isFocus) {
                // 强制 copy，先删除再 copy
                try {
                    outFile.delete()
                    outFile.createNewFile()
                } catch (e: Exception) {
                    e.printStackTrace()
                    return false
                }
            } else {
                // 非强制 copy，如果存在则不 copy
                return false
            }
        }

        var inputStream: InputStream? = null
        var fos: FileOutputStream? = null
        try {
            inputStream = context.assets.open(fullNameAndSuffix)
            fos = FileOutputStream(outFile)
            val buffer = ByteArray(8192)
            var byteCount = inputStream.read(buffer)
            while (byteCount != -1) {
                fos.write(buffer, 0, byteCount)
                byteCount = inputStream.read(buffer)
            }
            return true
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            try {
                fos?.flush()
                fos?.close()
                inputStream?.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return false
    }

}
