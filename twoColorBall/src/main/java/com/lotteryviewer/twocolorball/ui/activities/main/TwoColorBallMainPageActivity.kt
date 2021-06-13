package com.lotteryviewer.twocolorball.ui.activities.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.lotteryviewer.base.interfaces.FunctionNone
import com.lotteryviewer.base.ui.BaseWebViewActivity
import com.lotteryviewer.base.widget.MyWebViewClient
import com.lotteryviewer.twocolorball.R
import com.lotteryviewer.twocolorball.util.SingleBallHtmlUtil

/**
 * 双色球官方网站 开奖 主页
 */
class TwoColorBallMainPageActivity : BaseWebViewActivity() {

    companion object {

        // 福彩双色球网址
        private const val URL = "http://www.cwl.gov.cn/kjxx/ssq/"

        private const val MENU_ID_REFRESH = 0
        private const val MENU_ID_CHECK = 1

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setActionBarTitle(R.string.two_color_ball_title)

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
        menu?.add(
            Menu.NONE,
            MENU_ID_CHECK,
            Menu.NONE,
            getString(R.string.two_color_ball_check_menu)
        )

        menu?.getItem(MENU_ID_REFRESH)?.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)
        menu?.getItem(MENU_ID_CHECK)?.setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == MENU_ID_REFRESH) {
            refreshUrl()
            return true
        } else if (item.itemId == MENU_ID_CHECK) {

            // 用户可能会切换页面内的 选择框，切换了开奖日期
            // 所以此处需要再次爬取数据
            SingleBallHtmlUtil.getHtmlText(baseWebView, object : FunctionNone {
                override fun onCallBack() {
                    baseLoadingLayout?.postDelayed({
                        startActivity(
                            Intent(
                                this@TwoColorBallMainPageActivity,
                                TwoColorBallSingleCheckActivity::class.java
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
