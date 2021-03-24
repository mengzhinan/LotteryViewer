package com.lotteryviewer.base.util

import android.webkit.WebView
import com.lotteryviewer.base.interfaces.FunctionStringOne

/**
 * @Author: duke
 * @DateTime: 2021-03-24 17:13:10
 * @Description: 功能描述：
 *
 */
object BaseHtmlJSUtil {

    /**
     * 加载并执行 js 方法
     * @return 获得 js 方法返回值
     */
    fun loadAndCallJs(
        webView: WebView?,
        jsCode: String?,
        jsFunctionName: String?,
        callBack: FunctionStringOne?
    ) {

        webView ?: return
        jsCode ?: return
        jsFunctionName ?: return
        callBack ?: return

        try {
            // 加载 js 代码
            webView.loadUrl(jsCode)


            // 执行方法 1
            // webView.loadUrl("javascript:$jsFunctionName();")

            // 执行方法 2
            webView.evaluateJavascript("javascript:$jsFunctionName();") { value: String? ->
                callBack.onCallBack(value)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            callBack.onCallBack(null)
        }
    }

}
