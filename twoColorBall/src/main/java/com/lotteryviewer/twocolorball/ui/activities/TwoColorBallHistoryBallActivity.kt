package com.lotteryviewer.twocolorball.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.lotteryviewer.base.interfaces.FunctionStringOne
import com.lotteryviewer.base.ui.BaseWebViewActivity
import com.lotteryviewer.twocolorball.R
import com.lotteryviewer.twocolorball.ui.widget.MultiBallWebViewClient
import com.lotteryviewer.twocolorball.util.MultiBetBallHtmlUtil

/**
 * 双色球 历史开奖 页面
 */
class TwoColorBallHistoryBallActivity : BaseWebViewActivity() {

    companion object {
        // 历史中奖信息
        private const val URL = "http://www.cwl.gov.cn/kjxx/ssq/kjgg/"

        private const val MENU_ID_REFRESH = 0
        private const val MENU_ID_BET = 1

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setActionBarTitle(R.string.two_color_ball_multi_title)
        baseWebView?.webViewClient = MultiBallWebViewClient()

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
            getString(R.string.multi_ball_refresh_menu)
        )
        menu?.add(
            Menu.NONE,
            MENU_ID_BET,
            Menu.NONE,
            getString(R.string.multi_color_ball_bet_menu)
        )

        menu?.getItem(MENU_ID_REFRESH)?.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)
        menu?.getItem(MENU_ID_BET)?.setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER)

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
        } else if (item.itemId == MENU_ID_BET) {
            // 爬取历史开奖高频号码
            MultiBetBallHtmlUtil.getHtmlText(baseWebView, object : FunctionStringOne {
                override fun onCallBack(value: String?) {
                    baseLoadingLayout?.postDelayed({
                        startActivity(
                            Intent(
                                this@TwoColorBallHistoryBallActivity,
                                TwoColorBallHistoryAnalysisActivity::class.java
                            )
                        )
                    }, 50)
                }
            })

        }
        return super.onOptionsItemSelected(item)
    }

}
