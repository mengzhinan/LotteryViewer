package com.lotteryviewer.sunset.ui.activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.lotteryviewer.base.ui.BaseWebViewActivity
import com.lotteryviewer.base.util.TextUtil
import com.lotteryviewer.sunset.R

/**
 * 疫情信息查看
 */
class PneumoniaActivity : BaseWebViewActivity() {

    companion object {
        private const val MENU_ID_REFRESH = 0

        private const val URL: String =
            "https://voice.baidu.com/act/newpneumonia/newpneumonia/?from=osari_aladin_banner"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setActionBarTitle(R.string.sunset_pneumonia)

        refreshUrl()
    }

    private fun refreshUrl() {
        if (TextUtil.isNullOrEmpty(URL)) {
            return
        }
        baseWebView?.loadUrl(URL)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menu?.add(Menu.NONE, MENU_ID_REFRESH, Menu.NONE, getString(R.string.sunset_menu1))

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
            return true
        } else if (item.itemId == MENU_ID_REFRESH) {
            refreshUrl()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}
