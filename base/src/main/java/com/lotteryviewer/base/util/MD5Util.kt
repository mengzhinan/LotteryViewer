package com.lotteryviewer.base.util

import android.text.TextUtils
import java.io.File
import java.io.FileInputStream
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.*

/**
 * @Author: duke
 * @DateTime: 2021-06-17 17:38:38
 * @Description: 功能描述：计算 MD5 值
 *
 */
object MD5Util {

    private val HEX_CHARS =
        charArrayOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F')

    private val MESSAGE_DIGEST = MessageDigest.getInstance("MD5")

    fun md5_2(str: String?): String {
        if (str == null || TextUtils.isEmpty(str.trim())) {
            return ""
        }
        try {
            val messageDigest = MessageDigest.getInstance("MD5") ?: return ""
            val bytes = messageDigest.digest(str.trim().toByteArray()) ?: return ""
            val result = StringBuilder()
            for (b in bytes) {
                var temp = Integer.toHexString(b.toInt() and 0xFF).toUpperCase(Locale.getDefault())
                if (temp.length == 1) {
                    temp = "0$temp"
                }
                result.append(temp)
            }
            return result.toString()
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }
        return ""
    }

    /**
     * 转换字节数组为 16 进制值
     * 单个字节不足 2 位时，前面补充 0
     */
    private fun parseToHexBinary(data: ByteArray): String {
        val r = StringBuilder(data.size * 2)
        data.forEach { b ->
            val i = b.toInt()
            r.append(HEX_CHARS[i shr 4 and 0xF])
            r.append(HEX_CHARS[i and 0xF])
        }
        return r.toString()
    }

    /**
     * 对字符串进行 MD5 编码
     */
    fun getMD5(text: String?, defaultValue: String = ""): String {
        if (text == null || TextUtils.isEmpty(text.trim())) {
            return defaultValue
        }
        MESSAGE_DIGEST.reset()
        val bytes = MESSAGE_DIGEST.digest(text.toByteArray())
        return parseToHexBinary(bytes)
    }

    /**
     * 对文件内容进行 MD5 编码
     */
    fun getMD5(file: File?, defaultValue: String = ""): String {
        if (file?.exists() == false) {
            return defaultValue
        }
        val bufferArray = ByteArray(1024)
        var len = 0
        val fiStream = FileInputStream(file)

        try {
            MESSAGE_DIGEST.reset()
            while ((fiStream.read(bufferArray, 0, bufferArray.size).also { len = it }) != -1) {
                MESSAGE_DIGEST.update(bufferArray, 0, len)
            }
            return parseToHexBinary(MESSAGE_DIGEST.digest())
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            fiStream.close()
        }
        return defaultValue
    }

    //------------------------------
    // 扩展函数方式

    fun String.md5(): String {
        MESSAGE_DIGEST.reset()
        val bytes = MESSAGE_DIGEST.digest(this.toByteArray())
        return bytes.hex()
    }

    /**
     * x 表示以十六进制形式输出
     * 02 表示不足两位,，前面补0输出，如果超过两位，则以实际输出
     */
    fun ByteArray.hex(): String {
        return joinToString("") { "%02X".format(it) }
    }

}
