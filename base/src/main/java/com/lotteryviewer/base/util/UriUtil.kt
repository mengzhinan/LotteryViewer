package com.lotteryviewer.base.util

import android.net.Uri
import android.text.TextUtils
import java.net.URLDecoder

/**
 * @Author: duke
 * @DateTime: 2021-04-07 15:14:53
 * @Description: 功能描述：
 *
 */
object UriUtil {

    private const val CHARSET_UTF_8 = "UTF-8"

    @JvmStatic
    fun parseUriParam(uri: Uri?, key: String?): String? {
        if (uri == null || TextUtils.isEmpty(key)) {
            return null
        }
        var value: String? = null
        try {
            value = uri.getQueryParameter(key)?.trim()
            if (!value.isNullOrEmpty()) {
                value = URLDecoder.decode(value, CHARSET_UTF_8).trim()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return value
    }

}