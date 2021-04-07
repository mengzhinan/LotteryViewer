package com.lotteryviewer.base.util

import android.net.Uri
import java.net.URLDecoder

/**
 * @Author: duke
 * @DateTime: 2021-04-07 15:14:53
 * @Description: 功能描述：
 *
 */
object UriUtil {

    fun getUriParameter(
        uriString: String?,
        paramKey: String?,
        isNeedDecode: Boolean = true
    ): String {
        return try {
            getUriParameter(Uri.parse(uriString), paramKey, isNeedDecode)
        } catch (e: Exception) {
            ""
        }
    }

    fun getUriParameter(uri: Uri?, paramKey: String?, isNeedDecode: Boolean = true): String {
        return try {
            // var va = uri?.getQueryParameters("target")?.getOrNull(0)?:""
            var value = uri?.getQueryParameter(paramKey) ?: ""
            if (value.isNotBlank() && isNeedDecode) {
                value = decodeUriOrParam(value) ?: ""
            }
            value
        } catch (e: Exception) {
            e.printStackTrace()
            ""
        }
    }

    fun decodeUriOrParam(str: String?): String? {
        return try {
            URLDecoder.decode(str, "UTF-8").trim()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

}