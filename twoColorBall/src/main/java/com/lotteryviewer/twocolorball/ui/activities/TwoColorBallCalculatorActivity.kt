package com.lotteryviewer.twocolorball.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.lotteryviewer.base.interfaces.FunctionNone
import com.lotteryviewer.base.ui.BaseWebViewActivity
import com.lotteryviewer.twocolorball.R
import com.lotteryviewer.twocolorball.ui.widget.SingleBallWebViewClient
import com.lotteryviewer.twocolorball.util.SingleBallHtmlUtil

class TwoColorBallCalculatorActivity : BaseWebViewActivity() {

    companion object {

        // 福彩双色球网址
        private const val URL = "http://www.cwl.gov.cn/kjxx/ssq/"

        private const val MENU_ID_REFRESH = 0
        private const val MENU_ID_CHECK = 1
        private const val MENU_ID_HISTORY = 2

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setActionBarTitle(R.string.two_color_ball_title)

        // just pre grab html data
        baseWebView?.webViewClient = SingleBallWebViewClient()

        refreshUrl()
    }

    private fun refreshUrl() {
        baseWebView?.loadUrl(URL)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menu?.add(
            Menu.NONE,
            MENU_ID_REFRESH,
            Menu.NONE,
            getString(R.string.two_color_ball_refresh_menu)
        )
        menu?.add(
            Menu.NONE,
            MENU_ID_HISTORY,
            Menu.NONE,
            getString(R.string.two_color_ball_history_menu)
        )
        menu?.add(
            Menu.NONE,
            MENU_ID_CHECK,
            Menu.NONE,
            getString(R.string.two_color_ball_check_menu)
        )

        menu?.getItem(MENU_ID_REFRESH)?.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)
        menu?.getItem(MENU_ID_HISTORY)?.setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER)
        menu?.getItem(MENU_ID_CHECK)?.setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER)

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
        } else if (item.itemId == MENU_ID_HISTORY) {
            startActivity(
                Intent(
                    this@TwoColorBallCalculatorActivity,
                    HistoryBallActivity::class.java
                )
            )
        } else if (item.itemId == MENU_ID_CHECK) {

            // 用户可能会切换页面内的 选择框，切换了开奖日期
            // 所以此处需要再次爬取数据
            SingleBallHtmlUtil.getHtmlText(baseWebView, object : FunctionNone {
                override fun onCallBack() {
                    baseLoadingLayout?.postDelayed({
                        startActivity(
                            Intent(
                                this@TwoColorBallCalculatorActivity,
                                SingleBallCheckActivity::class.java
                            )
                        )
                    }, 50)
                }
            })

        }
        return super.onOptionsItemSelected(item)
    }

}
