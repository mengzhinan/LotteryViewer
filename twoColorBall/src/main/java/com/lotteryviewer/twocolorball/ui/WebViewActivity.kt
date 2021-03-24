package com.lotteryviewer.twocolorball.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.webkit.WebView
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import com.lotteryviewer.base.interfaces.FunctionNone
import com.lotteryviewer.base.util.WebViewSettingsUtil
import com.lotteryviewer.base.widget.MyWebChromeClient
import com.lotteryviewer.twocolorball.R
import com.lotteryviewer.twocolorball.util.BallHtmlUtil
import com.lotteryviewer.twocolorball.widget.BallWebViewClient

class WebViewActivity : AppCompatActivity() {

    companion object {
        // china fc web url
        private const val LOAD_URL = "http://www.cwl.gov.cn/kjxx/ssq/"
        private const val MENU_ID_REFRESH = 0
        private const val MENU_ID_CHECK = 1
    }

    private var webView: WebView? = null
    private var loadingLayout: RelativeLayout? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)
        initActionBar()
        initUI()
        refreshUrl()
    }

    private fun initActionBar() {
        // 返回按钮
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        // 是否显示标题
        supportActionBar?.setDisplayShowTitleEnabled(true)
        // 设置标题
        supportActionBar?.title = getString(R.string.title)
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initUI() {
        loadingLayout = findViewById(R.id.loading_layout)
        webView = findViewById(R.id.content_webview)

        webView?.webChromeClient = MyWebChromeClient()
        webView?.webViewClient = BallWebViewClient(loadingLayout)

        WebViewSettingsUtil.setWebView(webView)

    }

    private fun refreshUrl() {
        loadingLayout?.visibility = View.VISIBLE
        webView?.loadUrl(LOAD_URL)
    }

    private fun checkPrize() {
        startActivity(Intent(this, CheckPrizeActivity::class.java))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menu?.add(Menu.NONE, MENU_ID_REFRESH, Menu.NONE, getString(R.string.menu2))
        menu?.add(Menu.NONE, MENU_ID_CHECK, Menu.NONE, getString(R.string.menu1))

        menu?.getItem(MENU_ID_REFRESH)?.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)
        menu?.getItem(MENU_ID_CHECK)?.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            if (webView?.canGoBack() == true) {
                webView?.goBack()
            } else {
                finish()
            }
        } else if (item.itemId == MENU_ID_REFRESH) {
            refreshUrl()
        } else if (item.itemId == MENU_ID_CHECK) {
            loadingLayout?.visibility = View.VISIBLE
            BallHtmlUtil.getHtmlText(webView, object : FunctionNone {
                override fun onCallBack() {
                    loadingLayout?.postDelayed({
                        loadingLayout?.visibility = View.GONE
                        checkPrize()
                    }, 200)
                }
            })
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && webView?.canGoBack() == true) {
            webView?.goBack()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

}
