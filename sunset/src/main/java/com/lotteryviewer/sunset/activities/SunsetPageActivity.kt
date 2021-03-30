package com.lotteryviewer.sunset.activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.lotteryviewer.base.interfaces.FunctionNone
import com.lotteryviewer.base.ui.BaseWebViewActivity
import com.lotteryviewer.base.util.TextUtil
import com.lotteryviewer.sunset.R
import com.lotteryviewer.sunset.util.SunsetDataUtil
import com.lotteryviewer.sunset.util.SunsetHtmlUtil
import com.lotteryviewer.sunset.widgets.SunsetWebViewClient

class SunsetPageActivity : BaseWebViewActivity() {

    companion object {
        private const val MENU_ID_REFRESH = 0
        private const val MENU_ID_SEE_TODAY = 1
    }

    private var url: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        url = SunsetDataUtil.parseIntentUrl(intent)
        setActionBarTitle(R.string.sunset_title)
        baseWebView?.webViewClient = SunsetWebViewClient()
        refreshUrl()
    }

    private fun refreshUrl() {
        if (!TextUtil.isNullOrEmpty(url)) {
            baseWebView?.loadUrl(url!!)
        }
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
            if (baseWebView?.canGoBack() == true) {
                baseWebView?.goBack()
            } else {
                finish()
            }
        } else if (item.itemId == MENU_ID_REFRESH) {
            refreshUrl()
        } else if (item.itemId == MENU_ID_CHECK) {

            // 此处需要再次爬取数据
            SunsetHtmlUtil.getHtmlText(baseWebView, object : FunctionNone {
                override fun onCallBack() {

                }
            })

        }
        return super.onOptionsItemSelected(item)
    }

}
