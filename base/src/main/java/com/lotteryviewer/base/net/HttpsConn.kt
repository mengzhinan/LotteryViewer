package com.lotteryviewer.base.net

import java.net.URL
import java.security.SecureRandom
import javax.net.ssl.HttpsURLConnection
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.TrustManager

/**
 * @Author: duke
 * @DateTime: 2021-08-11 14:56:09
 * @Description: 功能描述：
 *
 */
object HttpsConn {

    fun getHttpsConn(url: String?): HttpsURLConnection? {
        val tempUrl = url?.trim()
        if (tempUrl == null || tempUrl.isNullOrBlank()) {
            return null
        }
        val urlObject = URL(tempUrl)
        // 创建SSLContext对象，并使用我们指定的信任管理器初始化
        val tm: Array<TrustManager> = arrayOf(X509TrustManagerImpl())
        val sslContext: SSLContext = SSLContext.getInstance("SSL")
        sslContext.init(null, tm, SecureRandom())
        // 从上述SSLContext对象中得到SSLSocketFactory对象
        val ssf: SSLSocketFactory = sslContext.socketFactory

        val conn = urlObject.openConnection() as? HttpsURLConnection

        conn?.sslSocketFactory = ssf

        return conn
    }

}