package com.lotteryviewer.base.net

import android.os.Handler
import android.os.Looper
import android.util.Log
import java.io.InputStream
import java.net.HttpURLConnection
import java.nio.charset.Charset
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

/**
 * @Author: duke
 * @DateTime: 2021-08-10 13:37:14
 * @Description: 功能描述：
 *
 */
class NetHelper {

    companion object {
        private const val TAG = "net_helper"
    }

    private var handler: Handler? = Handler(Looper.getMainLooper())
    private var executor: ExecutorService? = Executors.newFixedThreadPool(2)

    fun clearResources() {
        handler?.removeCallbacksAndMessages(null)
        executor?.shutdown()
    }

    /**
     * GET 请求 (HttpURLConnection)
     */
    fun getHttpSync(url: String?, param: Map<String, Any>?, callback: HttpCallback?) {
        executor?.submit {

            val newUrl = assembleUrlParam(url, param)
            if (newUrl.isBlank()) {
                handler?.post {
                    callback?.onFailure("newUrl.isBlank()")
                }
                return@submit
            }

            var inS: InputStream? = null
            val result: String?
            var conn: HttpURLConnection? = null
            try {
                Log.d(TAG, "GrowthNet newUrl = $newUrl")

                conn = HttpsConn.getHttpsConn(newUrl)

                conn?.connect()
                inS = conn?.inputStream
                val buffer = ByteArray(inS?.available() ?: 0)
                inS?.read(buffer)
                result = String(buffer, Charset.defaultCharset())

                handler?.post {
                    callback?.onSuccess(
                        result,
                        conn?.responseCode ?: 0,
                        conn?.responseMessage
                    )
                }

            } catch (e: Exception) {

                handler?.post {
                    callback?.onFailure(e.message)
                }

                e.printStackTrace()
            } finally {
                try {
                    inS?.close()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                try {
                    conn?.disconnect()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

        }
    }

    /**
     * GET 请求 (OkHttpClient)
     */
    fun getOkHttpSync(url: String?, param: Map<String, Any>?, callback: HttpCallback?) {
        executor?.submit {

            val newUrl = assembleUrlParam(url, param)
            if (newUrl.isBlank()) {
                handler?.post {
                    callback?.onFailure("newUrl.isBlank()")
                }
                return@submit
            }

//            val client = OkHttpClient()
//            val request = Request.Builder()
//                    .url(newUrl)
//                    .build()

            var result = ""

            try {
                Log.d(TAG, "GrowthNet newUrl = $newUrl")

//                val response = client.newCall(request).execute()
//                result = response?.body()?.string() ?: ""

//                handler?.post {
//                    callback?.onSuccess(result,
//                            response.code(),
//                            response?.message())
//                }

            } catch (e: Exception) {

                handler?.post {
                    callback?.onFailure(e.message)
                }

                e.printStackTrace()
            }

        }
    }

    /**
     * 拼接 url 参数
     */
    private fun assembleUrlParam(url: String?, param: Map<String, Any>?): String {
        var newUrl = url?.trim() ?: ""
        if (newUrl.isBlank()) {
            return newUrl
        }

        val size = param?.size ?: 0
        if (param == null || size == 0) {
            return newUrl
        }

        newUrl += if (newUrl.contains("?")) {
            "&"
        } else {
            "?"
        }

        val buffer = StringBuilder()
        var index = 0
        for ((k, v) in param) {
            buffer.append(k)
            buffer.append("=")
            buffer.append(v)
            index++
            if (index < size) {
                buffer.append("&")
            }
        }
        return newUrl + buffer
    }

}