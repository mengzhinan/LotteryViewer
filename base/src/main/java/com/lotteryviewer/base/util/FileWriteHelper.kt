package com.lotteryviewer.base.util

import android.text.TextUtils
import java.io.*
import java.nio.charset.Charset

/**
 * @ Author: duke
 * @ DateTime: 2018-10-02 07:43
 * @ Description: 简单的 写入文件 工具类
 * 需要权限："android.permission.WRITE_EXTERNAL_STORAGE"
 * version： 3.0
 * Modify：2021-03-31
 */
class FileWriteHelper private constructor(
    private val file: File
) {

    private var fileOutputStream: FileOutputStream? = null
    private var outputStreamWriter: OutputStreamWriter? = null
    private var bufferedWriter: BufferedWriter? = null

    companion object {

        fun openStream(file: File): FileWriteHelper? {

            return openStream(file, Charset.forName("UTF-8"), false)
        }

        fun openStream(
            file: File,
            charset: Charset? = Charset.forName("UTF-8"),
            isAppend: Boolean = false
        ): FileWriteHelper? {

            return FileWriteHelper(file).createStream(
                isAppend,
                charset ?: Charset.defaultCharset()
            )
        }

        fun isFileExists(fileName: String): Boolean {
            val file = File(fileName)
            return file.exists()
        }

        fun isFileExists(baseFolder: String?, fileName: String): Boolean {
            val file = File(baseFolder, fileName)
            return file.exists()
        }

        fun isFileExists(baseFolder: File?, fileName: String): Boolean {
            val file = File(baseFolder, fileName)
            return file.exists()
        }

    }

    fun getFile(): File {
        return file
    }

    private fun createStream(isAppend: Boolean, charset: Charset): FileWriteHelper? {
        try {
            if (!file.exists()) {
                file.parentFile?.mkdirs()
                file.createNewFile()
            }
            /**
             * isAppend 参数：
             * 控制是否在上一次打开流的连续读写内容之后追加内容。
             * 与本次打开流后的连续读写无关。
             * 默认不保留以前的内容，只保留当前流的一系列写入操作。
             */
            fileOutputStream = FileOutputStream(file, isAppend)

            //Android-changed: Use UTF_8 unconditionally.
            outputStreamWriter = OutputStreamWriter(fileOutputStream, charset)

            //default output-buffer size is 8192
            bufferedWriter = BufferedWriter(outputStreamWriter)
            return this
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    fun write(content: String?): FileWriteHelper? {
        if (TextUtils.isEmpty(content)) {
            return this
        }
        try {
            bufferedWriter?.write(content)
            return this
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return null
    }

    fun newLine(): FileWriteHelper? {
        try {
            bufferedWriter?.newLine()
            return this
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return null
    }

    fun closeStream() {
        try {
            bufferedWriter?.flush()
            bufferedWriter?.close()
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            bufferedWriter = null
        }
        try {
            outputStreamWriter?.flush()
            outputStreamWriter?.close()
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            outputStreamWriter = null
        }
        try {
            fileOutputStream?.flush()
            fileOutputStream?.close()
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            fileOutputStream = null
        }
    }

}
