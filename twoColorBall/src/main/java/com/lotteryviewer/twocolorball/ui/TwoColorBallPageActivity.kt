package com.lotteryviewer.twocolorball.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.lotteryviewer.base.interfaces.FunctionNone
import com.lotteryviewer.base.ui.WebViewActivity
import com.lotteryviewer.twocolorball.R
import com.lotteryviewer.twocolorball.util.BallHtmlUtil
import com.lotteryviewer.twocolorball.widget.BallWebViewClient

class TwoColorBallPageActivity : WebViewActivity() {

    companion object {
        // china fc web url
        private const val LOAD_URL = "http://www.cwl.gov.cn/kjxx/ssq/"
        private const val MENU_ID_REFRESH = 0
        private const val MENU_ID_CHECK = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        baseWebView?.webViewClient = BallWebViewClient(baseLoadingLayout)

        refreshUrl()
    }


    private fun refreshUrl() {
        baseWebView?.loadUrl(LOAD_URL)
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
            baseLoadingLayout?.visibility = View.VISIBLE
            BallHtmlUtil.getHtmlText(baseWebView, object : FunctionNone {
                override fun onCallBack() {
                    baseLoadingLayout?.postDelayed({
                        baseLoadingLayout?.visibility = View.GONE
                        startActivity(
                            Intent(
                                this@TwoColorBallPageActivity,
                                TwoColorBallCheckActivity::class.java
                            )
                        )
                    }, 200)
                }
            })
        }
        return super.onOptionsItemSelected(item)
    }

}
