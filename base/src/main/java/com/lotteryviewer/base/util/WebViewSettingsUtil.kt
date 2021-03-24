package com.lotteryviewer.base.util

import android.annotation.SuppressLint
import android.webkit.WebSettings
import android.webkit.WebView

/**
 * @Author: duke
 * @DateTime: 2021-03-17 12:48:33
 * @Description: 功能描述：
 */
object WebViewSettingsUtil {

    @SuppressLint("SetJavaScriptEnabled")
    fun setWebView(webView: WebView?) {

        val webSettings = webView?.settings

        // 支持 js 调用
        webSettings?.javaScriptEnabled = true
        // 设置自适应屏幕，两者合用
        // 将图片调整到适合 WebView 的大小
        webSettings?.useWideViewPort = true
        // 缩放至屏幕的大小
        webSettings?.loadWithOverviewMode = true
        // 支持缩放，默认为true。是下面那个的前提。
        webSettings?.setSupportZoom(true)
        // 设置内置的缩放控件，是否支持缩放。若为false，则该WebView不可缩放
        webSettings?.builtInZoomControls = true
        // 显示原生的缩放控件按钮
        webSettings?.displayZoomControls = false
        // 关闭webview中缓存
        webSettings?.cacheMode = WebSettings.LOAD_NO_CACHE
        // 设置不可以访问文件
        webSettings?.allowFileAccess = false
        // 不支持通过JS打开新窗口
        webSettings?.javaScriptCanOpenWindowsAutomatically = false
        // 支持自动加载图片
        webSettings?.loadsImagesAutomatically = true
        // 设置编码格式
        webSettings?.defaultTextEncodingName = "UTF-8"

    }

}
