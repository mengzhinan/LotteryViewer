package com.lotteryviewer.twocolorball.util

import android.webkit.WebView
import com.lotteryviewer.base.interfaces.FunctionNone
import com.lotteryviewer.base.interfaces.FunctionStringOne
import com.lotteryviewer.base.util.BaseHtmlJSUtil

/**
 * author: duke
 * version: 1.0
 * dateTime: 2021-05-14 15:03
 * description: 双色球计算器 去广告工具类
 *
 */
object ChartHtmlUtil {

    // 移除节点 UI
    private const val removeTopADs = ("javascript:function removeTopADs() {"
            + "    var ads = document.getElementsByClassName('tb-gg-img');"
            + "    var totalCount = ads.length;"
            + "    for (var i = 0; i < totalCount; i++) {"
            + "        var ad = ads[i];"
            + "        ad.parentNode.removeChild(ad);"
            + "    }"
            + "}")

    private const val removeRightADs = ("javascript:function removeRightADs() {"
            + "    var ads = document.getElementsByClassName('jltb');"
            + "    var totalCount = ads.length;"
            + "    for (var i = 0; i < totalCount; i++) {"
            + "        var ad = ads[i];"
            + "        ad.parentNode.removeChild(ad);"
            + "    }"
            + "}")

    fun removeADs(view: WebView?, endCallback: FunctionNone?) {
        view ?: return

        var top = false
        var right = false

        BaseHtmlJSUtil.loadAndCallJs(
            view,
            removeTopADs,
            "removeTopADs",
            object : FunctionStringOne {
                override fun onCallBack(value: String?) {
                    top = true
                    callback(top, right, endCallback)
                }
            })

        BaseHtmlJSUtil.loadAndCallJs(
            view,
            removeRightADs,
            "removeRightADs",
            object : FunctionStringOne {
                override fun onCallBack(value: String?) {
                    right = true
                    callback(top, right, endCallback)
                }
            })

    }

    private fun callback(
        topSuccess: Boolean,
        rightSuccess: Boolean,
        endCallback: FunctionNone?
    ) {
        if (topSuccess && rightSuccess) {
            endCallback?.onCallBack()
        }
    }

}
