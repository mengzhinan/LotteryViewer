package com.lotteryviewer.twocolorball.widget

import android.graphics.Bitmap
import android.view.View
import android.webkit.WebView
import com.lotteryviewer.base.interfaces.FunctionNone
import com.lotteryviewer.base.widget.MyWebViewClient
import com.lotteryviewer.twocolorball.util.BallHtmlUtil
import java.lang.ref.WeakReference

/**
 * @Author: duke
 * @DateTime: 2021-03-24 18:21:21
 * @Description: 功能描述：
 *
 */
class BallWebViewClient(hideView: View? = null) : MyWebViewClient() {

    private val weakReferenceLoading: WeakReference<View> = WeakReference(hideView)

    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
        super.onPageStarted(view, url, favicon)
        weakReferenceLoading.get()?.visibility = View.VISIBLE
    }

    override fun onPageFinished(view: WebView, url: String) {
        super.onPageFinished(view, url)

        BallHtmlUtil.getHtmlText(view, object : FunctionNone {
            override fun onCallBack() {
                view.postDelayed({
                    weakReferenceLoading.get()?.visibility = View.GONE
                }, 100)
            }
        })

    }

}