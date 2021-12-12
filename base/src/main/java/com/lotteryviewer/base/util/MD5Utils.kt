package com.lotteryviewer.base.util

import android.text.TextUtils
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.*

/**
 * @Author: duke
 * @DateTime: 2021-12-10 16:47:24
 * @Description: 功能描述：
 *
 */
object MD5Utils {

    fun md5(str: String?): String {
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

}