package com.lotteryviewer.base.net

import android.annotation.SuppressLint
import java.security.cert.X509Certificate
import javax.net.ssl.X509TrustManager

/**
 * @Author: duke
 * @DateTime: 2021-08-11 13:29:59
 * @Description: 功能描述：
 *
 */
@SuppressLint("CustomX509TrustManager")
class X509TrustManagerImpl : X509TrustManager {

    /**
     * 检查客户端证书
     */
    @SuppressLint("TrustAllX509TrustManager")
    override fun checkClientTrusted(chain: Array<out X509Certificate>?, authType: String?) {

    }

    /**
     * 检查服务器端证书
     */
    @SuppressLint("TrustAllX509TrustManager")
    override fun checkServerTrusted(chain: Array<out X509Certificate>?, authType: String?) {

    }

    /**
     * 返回受信任的X509证书数组
     */
    override fun getAcceptedIssuers(): Array<X509Certificate> {
        return arrayOf()
    }

}
