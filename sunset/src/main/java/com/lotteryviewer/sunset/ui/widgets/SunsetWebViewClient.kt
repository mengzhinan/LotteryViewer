package com.lotteryviewer.sunset.ui.widgets

import android.webkit.WebView
import com.lotteryviewer.base.interfaces.FunctionNone
import com.lotteryviewer.base.widget.MyWebViewClient
import com.lotteryviewer.sunset.util.SunsetHtmlUtil

/**
 * @Author: duke
 * @DateTime: 2021-03-30 15:54:07
 * @Description: 功能描述：
 *
 */
class SunsetWebViewClient : MyWebViewClient() {

    override fun onPageFinished(view: WebView?, url: String?) {
        super.onPageFinished(view, url)
        SunsetHtmlUtil.getHtmlText(view, object : FunctionNone {
            override fun onCallBack() {
                // do nothing
            }
        })
    }

}