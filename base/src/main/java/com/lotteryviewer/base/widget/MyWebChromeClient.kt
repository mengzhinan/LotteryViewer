package com.lotteryviewer.base.widget

import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import java.lang.ref.WeakReference

/**
 * @Author: duke
 * @DateTime: 2021-03-24 16:45:48
 * @Description: 功能描述：
 */
open class MyWebChromeClient(loading: View? = null) : WebChromeClient() {

    private var weakReferenceLoading: WeakReference<View>? = WeakReference(loading)

    private fun setVisibleLoadingView(visible: Int) {
        weakReferenceLoading?.get()?.visibility = visible
    }

    override fun onProgressChanged(view: WebView, newProgress: Int) {
        if (newProgress >= 100) {
            setVisibleLoadingView(View.GONE)
        }
    }

    override fun onShowCustomView(view: View, callback: CustomViewCallback) {
        super.onShowCustomView(view, callback)
        setVisibleLoadingView(View.VISIBLE)
    }

    override fun onHideCustomView() {
        super.onHideCustomView()
        setVisibleLoadingView(View.GONE)
    }

}