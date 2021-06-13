package com.lotteryviewer.twocolorball.ui.activities.history

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.lotteryviewer.base.interfaces.FunctionStringOne
import com.lotteryviewer.base.ui.BaseWebViewActivity
import com.lotteryviewer.base.widget.MyWebViewClient
import com.lotteryviewer.twocolorball.R
import com.lotteryviewer.twocolorball.util.HistoryBallHtmlUtil

/**
 * 双色球 历史开奖 页面
 */
class TwoColorBallHistoryMainActivity : BaseWebViewActivity() {

    companion object {
        // 历史中奖信息
        private const val URL = "http://www.cwl.gov.cn/kjxx/ssq/kjgg/"

        private const val MENU_ID_REFRESH = 0
        private const val MENU_ID_ANALYSIS = 1

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setActionBarTitle(R.string.two_color_ball_multi_title)
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
            getString(R.string.history_ball_refresh_menu)
        )
        menu?.add(
            Menu.NONE,
            MENU_ID_ANALYSIS,
            Menu.NONE,
            getString(R.string.history_ball_analysis_menu)
        )

        menu?.getItem(MENU_ID_REFRESH)?.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)
        menu?.getItem(MENU_ID_ANALYSIS)?.setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == MENU_ID_REFRESH) {
            refreshUrl()
            return true
        } else if (item.itemId == MENU_ID_ANALYSIS) {
            // 爬取历史开奖高频号码
            HistoryBallHtmlUtil.getHtmlText(baseWebView, object : FunctionStringOne {
                override fun onCallBack(value: String?) {
                    baseLoadingLayout?.postDelayed({
                        startActivity(
                            Intent(
                                this@TwoColorBallHistoryMainActivity,
                                TwoColorBallHistoryAnalysisActivity::class.java
                            )
                        )
                    }, 50)
                }
            })
            return true

        }
        return super.onOptionsItemSelected(item)
    }

}
