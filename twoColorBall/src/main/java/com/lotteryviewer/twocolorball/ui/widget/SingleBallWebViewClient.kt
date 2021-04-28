package com.lotteryviewer.twocolorball.ui.widget

import android.webkit.WebView
import com.lotteryviewer.base.widget.MyWebViewClient

/**
 * @Author: duke
 * @DateTime: 2021-03-24 18:21:21
 * @Description: 功能描述：
 *
 */
class SingleBallWebViewClient() : MyWebViewClient() {

    override fun onPageFinished(view: WebView, url: String) {
        super.onPageFinished(view, url)

    }

}