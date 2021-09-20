package com.lotteryviewer.sunset.ui.activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.lotteryviewer.base.interfaces.FunctionNone
import com.lotteryviewer.base.ui.BaseWebViewActivity
import com.lotteryviewer.base.util.TextUtil
import com.lotteryviewer.sunset.R
import com.lotteryviewer.sunset.ui.dialog.SunsetTodayDialogUtil
import com.lotteryviewer.sunset.ui.widgets.SunsetWebViewClient
import com.lotteryviewer.sunset.util.SunsetDataUtil
import com.lotteryviewer.sunset.util.SunsetHtmlUtil

/**
 * 日出日落
 */
class SunsetActivity : BaseWebViewActivity() {

    companion object {
        private const val MENU_ID_REFRESH = 0
        private const val MENU_ID_SEE_TODAY = 1
    }

    private var url: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 解析对应的 url
        url = SunsetDataUtil.parseIntentUrl(intent)

        setActionBarTitle(R.string.sunset_title)

        // WebViewClient 中解析 html 数据
        baseWebView?.webViewClient = SunsetWebViewClient()

        refreshUrl()
    }

    override fun onResume() {
        super.onResume()

        baseWebView?.postDelayed({
            seeToday()
        }, 1000)

    }

    private fun refreshUrl() {
        if (TextUtil.isNullOrEmpty(url)) {
            return
        }
        baseWebView?.loadUrl(url)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menu?.add(Menu.NONE, MENU_ID_REFRESH, Menu.NONE, getString(R.string.sunset_menu1))
        menu?.add(Menu.NONE, MENU_ID_SEE_TODAY, Menu.NONE, getString(R.string.sunset_menu2))

        menu?.getItem(MENU_ID_REFRESH)?.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)
        menu?.getItem(MENU_ID_SEE_TODAY)?.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            if (baseWebView?.canGoBack() == true) {
                baseWebView?.goBack()
            } else {
                finish()
            }
            return true
        } else if (item.itemId == MENU_ID_REFRESH) {
            refreshUrl()
            return true
        } else if (item.itemId == MENU_ID_SEE_TODAY) {

            seeToday()

            return true

        }
        return super.onOptionsItemSelected(item)
    }

    private fun seeToday() {
        // 此处需要再次爬取数据
        SunsetHtmlUtil.getHtmlText(baseWebView, object : FunctionNone {
            override fun onCallBack() {
                SunsetTodayDialogUtil.showTodayDialog(this@SunsetActivity)
            }
        })
    }
}
