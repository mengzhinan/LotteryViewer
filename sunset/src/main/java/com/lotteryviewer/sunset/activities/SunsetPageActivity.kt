package com.lotteryviewer.sunset.activities

import android.os.Bundle
import android.webkit.WebView
import android.widget.Toast
import com.lotteryviewer.base.interfaces.FunctionNone
import com.lotteryviewer.base.ui.BaseWebViewActivity
import com.lotteryviewer.base.widget.MyWebViewClient
import com.lotteryviewer.sunset.util.SunsetDataUtil
import com.lotteryviewer.sunset.util.SunsetHtmlUtil

class SunsetPageActivity : BaseWebViewActivity() {

    private var url: String? = ""

    private val myWebViewClient = object : MyWebViewClient() {
        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            SunsetHtmlUtil.getHtmlText(view, object : FunctionNone {
                override fun onCallBack() {
                    Toast.makeText(
                        this@SunsetPageActivity,
                        "${SunsetDataUtil.getDateStr()}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        url = SunsetDataUtil.parseIntentUrl(intent)
        baseWebView?.webViewClient = myWebViewClient
        baseWebView?.loadUrl(SunsetDataUtil.URL_BEIJING)
    }


}
