package com.lotteryviewer.base.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.KeyEvent
import android.webkit.WebView
import android.widget.RelativeLayout
import androidx.annotation.StringRes
import androidx.constraintlayout.widget.ConstraintLayout
import com.lotteryviewer.base.R
import com.lotteryviewer.base.util.WebViewSettingsUtil
import com.lotteryviewer.base.widget.MyWebChromeClient
import com.lotteryviewer.base.widget.MyWebViewClient

open class BaseWebViewActivity : BaseActivity() {

    protected var baseActivityRoot: ConstraintLayout? = null
    protected var baseWebView: WebView? = null
    protected var baseLoadingLayout: RelativeLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base_web_view)
        initUI()
        initActionBar()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initUI() {
        baseActivityRoot = findViewById(R.id.activity_web_view_root)
        baseLoadingLayout = findViewById(R.id.loading_layout)
        baseWebView = findViewById(R.id.content_web_view)

        baseWebView?.webChromeClient = MyWebChromeClient(baseLoadingLayout)
        baseWebView?.webViewClient = MyWebViewClient()

        // set WebView common properties
        WebViewSettingsUtil.setWebView(baseWebView)

    }

    private fun initActionBar() {
        // 返回按钮
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        // 是否显示标题
        supportActionBar?.setDisplayShowTitleEnabled(true)
    }

    protected fun setActionBarTitle(@StringRes titleResId: Int?) {
        titleResId ?: return
        setActionBarTitle(getString(titleResId))
    }

    protected fun setActionBarTitle(title: String?) {
        title ?: return
        supportActionBar?.title = title
    }

    /**
     * 设置 loading 的背景颜色
     */
    protected fun setLoadingCoverColor(colorInt: Int) {
        baseLoadingLayout?.setBackgroundColor(colorInt)
    }

    /**
     * 支持 WebView 返回
     */
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && baseWebView?.canGoBack() == true) {
            baseWebView?.goBack()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

}
