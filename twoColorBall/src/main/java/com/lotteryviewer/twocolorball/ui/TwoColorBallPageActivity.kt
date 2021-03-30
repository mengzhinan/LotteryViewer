package com.lotteryviewer.twocolorball.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.lotteryviewer.base.interfaces.FunctionNone
import com.lotteryviewer.base.ui.BaseWebViewActivity
import com.lotteryviewer.twocolorball.R
import com.lotteryviewer.twocolorball.util.TwoColorBallDataUtil
import com.lotteryviewer.twocolorball.util.TwoColorBallHtmlUtil
import com.lotteryviewer.twocolorball.widget.BallWebViewClient

class TwoColorBallPageActivity : BaseWebViewActivity() {

    companion object {

        private const val MENU_ID_REFRESH = 0
        private const val MENU_ID_CHECK = 1

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setActionBarTitle(R.string.two_color_ball_title)

        // just pre grab html data
        baseWebView?.webViewClient = BallWebViewClient()

        refreshUrl()
    }

    private fun refreshUrl() {
        baseWebView?.loadUrl(TwoColorBallDataUtil.TWO_COLOR_BALL_URL)
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

            // 用户可能会切换页面内的 选择框，切换了开奖日期
            // 所以此处需要再次爬取数据
            TwoColorBallHtmlUtil.getHtmlText(baseWebView, object : FunctionNone {
                override fun onCallBack() {
                    baseLoadingLayout?.postDelayed({
                        startActivity(
                            Intent(
                                this@TwoColorBallPageActivity,
                                TwoColorBallCheckActivity::class.java
                            )
                        )
                    }, 50)
                }
            })

        }
        return super.onOptionsItemSelected(item)
    }

}
