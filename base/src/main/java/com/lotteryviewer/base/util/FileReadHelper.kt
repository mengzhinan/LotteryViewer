package com.lotteryviewer.base.util

import java.io.*
import java.nio.charset.Charset

/**
 * author: duke
 * dateTime: 2018-10-28 10:14
 * description: 读取文件数据工具类 <br></br>
 * Version：3.0 <br></br>
 * Modify：2021-03-31
 */
class FileReadHelper private constructor(inputStream: InputStream?) {

    private var inputStream: InputStream?
    private var inputStreamReader: InputStreamReader? = null
    private var reader: BufferedReader? = null

    fun readString(): String? {
        if (inputStream == null) {
            return null
        }
        try {
            inputStreamReader = InputStreamReader(inputStream, Charset.defaultCharset())
            // 默认 size 就是 8192
            reader = BufferedReader(inputStreamReader)
            val stringBuffer = StringBuilder()
            var text: String?
            while (reader!!.readLine().also { text = it } != null) {
                stringBuffer.append(text)
            }
            if (stringBuffer.isNotEmpty()) {
                return stringBuffer.toString()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            closeStream()
        }
        return null
    }

    fun readByteArray(): ByteArray? {
        if (inputStream == null) {
            return null
        }
        try {
            //获取文件流的大小
            val size = inputStream!!.available()
            val buffer = ByteArray(size)
            val total = inputStream!!.read(buffer)
            if (total >= 0) {
                return buffer
            }
        } catch (e: Exception) {
            e.printStackTrace()
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

    companion object {
        fun openStream(filePath: String): FileReadHelper {
            return openStream(File(filePath))
        }

        fun openStream(file: File): FileReadHelper {
            return FileReadHelper(FileInputStream(file))
        }

        fun openStream(inputStream: InputStream?): FileReadHelper {
            return FileReadHelper(inputStream)
        }
    }

    init {
        this.inputStream = inputStream
    }
}
