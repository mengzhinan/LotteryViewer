package com.lotteryviewer.twocolorball.ui.widget

import android.webkit.WebView
import android.widget.Toast
import com.lotteryviewer.base.app.BaseApplication
import com.lotteryviewer.base.interfaces.FunctionNone
import com.lotteryviewer.base.widget.MyWebViewClient
import com.lotteryviewer.twocolorball.util.CalculatorHtmlUtil

/**
 * author: duke
 * version: 1.0
 * dateTime: 2021-05-14 15:04
 * description:
 *
 */
class CalculatorWebViewClient : MyWebViewClient() {

    override fun onPageFinished(view: WebView?, url: String?) {
        super.onPageFinished(view, url)

        CalculatorHtmlUtil.removeADs(view, object : FunctionNone {
            override fun onCallBack() {
                Toast.makeText(BaseApplication.get(), "去广告完毕!", Toast.LENGTH_SHORT).show()
            }
        })

    }

}
