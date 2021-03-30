package com.lotteryviewer.twocolorball.widget

import android.webkit.WebView
import com.lotteryviewer.base.interfaces.FunctionNone
import com.lotteryviewer.base.widget.MyWebViewClient
import com.lotteryviewer.twocolorball.util.TwoColorBallHtmlUtil

/**
 * @Author: duke
 * @DateTime: 2021-03-24 18:21:21
 * @Description: 功能描述：
 *
 */
class BallWebViewClient() : MyWebViewClient() {

    override fun onPageFinished(view: WebView, url: String) {
        super.onPageFinished(view, url)

        // just pre grab html data

        TwoColorBallHtmlUtil.getHtmlText(view, object : FunctionNone {
            override fun onCallBack() {
                // do nothing
            }
        })

    }

}