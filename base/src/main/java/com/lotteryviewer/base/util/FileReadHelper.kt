package com.lotteryviewer.base.util

import android.text.TextUtils
import java.io.*
import java.nio.charset.Charset

/**
 * author: duke
 * dateTime: 2018-10-28 10:14
 * description: 读取文件数据工具类
 * Version：3.0
 * Modify：2021-03-31
 */
class FileReadHelper private constructor(private var inputStream: InputStream?) {

    private var inputStreamReader: InputStreamReader? = null
    private var reader: BufferedReader? = null

    companion object {

        var ERROR_INFO: String? = ""

        private val defaultCharset = Charset.defaultCharset()

        fun openStream(filePath: String?): FileReadHelper? {
            if (filePath == null || TextUtils.isEmpty(filePath.trim())) {
                ERROR_INFO = "FileReadHelper.openStream() filePath == null or empty"
                return null
            }
            return openStream(File(filePath))
        }

        fun openStream(file: File?): FileReadHelper? {
            if (file == null || !file.exists()) {
                ERROR_INFO = "FileReadHelper.openStream() file == null || !file.exists()"
                return null
            }
            return FileReadHelper(FileInputStream(file))
        }

        fun openStream(inputStreamParam: InputStream?): FileReadHelper? {
            if (inputStreamParam == null) {
                ERROR_INFO = "FileReadHelper.openStream() inputStreamParam == null"
                return null
            }
            return FileReadHelper(inputStreamParam)
        }

    }

    fun readString(charset: Charset? = defaultCharset): String? {
        if (inputStream == null) {
            ERROR_INFO = "FileReadHelper.readString() inputStream == null"
            return null
        }
        try {
            inputStreamReader = InputStreamReader(inputStream, charset ?: defaultCharset)

            // 默认 size 就是 8192
            reader = BufferedReader(inputStreamReader)

            val stringBuffer = StringBuilder()
            var text: String?
            while (reader?.readLine().also { text = it } != null) {
                stringBuffer.append(text)
            }
            if (stringBuffer.isNotEmpty()) {
                return stringBuffer.toString()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            ERROR_INFO = "FileReadHelper.readString() 失败：${e.message}"
        } finally {
            closeStream()
        }
        return null
    }

    fun readByteArray(): ByteArray? {
        if (inputStream == null) {
            ERROR_INFO = "FileReadHelper.readByteArray() inputStream == null"
            return null
        }
        try {
            //获取文件流的大小
            val size = inputStream?.available() ?: return null
            val buffer = ByteArray(size)
            val total = inputStream?.read(buffer) ?: -1
            if (total >= 0) {
                return buffer
            }
        } catch (e: Exception) {
            e.printStackTrace()
            ERROR_INFO = "FileReadHelper.readByteArray() 失败：${e.message}"
        } finally {
            closeStream()
        }
        return null
    }

    private fun closeStream() {
        try {
            reader?.close()
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            reader = null
        }

        try {
            inputStreamReader?.close()
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            inputStreamReader = null
        }

        try {
            inputStream?.close()
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            inputStream = null
        }
    }

}
