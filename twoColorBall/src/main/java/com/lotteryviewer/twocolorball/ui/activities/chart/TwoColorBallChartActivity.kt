package com.lotteryviewer.twocolorball.ui.activities.chart

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.lotteryviewer.base.ui.BaseWebViewActivity
import com.lotteryviewer.base.widget.MyWebViewClient
import com.lotteryviewer.twocolorball.R

/**
 * 双色球 数据图表 页面
 */
class TwoColorBallChartActivity : BaseWebViewActivity() {

    companion object {

        // 双色球图表 网址
        private const val URL = "https://www.zhcw.com/sjtb/ssq/"

        private const val MENU_ID_REFRESH = 0

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setActionBarTitle(R.string.two_color_ball_chart_title)

        // just pre grab html data
        baseWebView?.webViewClient = MyWebViewClient()

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

        menu?.getItem(MENU_ID_REFRESH)?.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)

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
        }
        return super.onOptionsItemSelected(item)
    }

}
