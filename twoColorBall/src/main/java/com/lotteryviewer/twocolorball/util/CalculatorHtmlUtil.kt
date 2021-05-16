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
object CalculatorHtmlUtil {

    // 移除节点 UI
    private const val removeTopADs = ("javascript:function removeTopADs() {"
            + "    var ads = document.getElementsByClassName('page-ads');"
            + "    var totalCount = ads.length;"
            + "    for (var i = 0; i < totalCount; i++) {"
            + "        var ad = ads[i];"
            + "        ad.parentNode.removeChild(ad);"
            + "    }"
            + "}")

    private const val removeBottomADs = ("javascript:function removeBottomADs() {"
            + "    var ads = document.getElementsByClassName('section clearfix');"
            + "    var totalCount = ads.length;"
            + "    for (var i = 0; i < totalCount; i++) {"
            + "        var ad = ads[i];"
            + "        ad.parentNode.removeChild(ad);"
            + "    }"
            + "}")

    private const val removeRightADs = ("javascript:function removeRightADs() {"
            + "    var ads = document.getElementsByClassName('_iibuxfuxlbc');"
            + "    var totalCount = ads.length;"
            + "    for (var i = 0; i < totalCount; i++) {"
            + "        var ad = ads[i];"
            + "        ad.parentNode.removeChild(ad);"
            + "    }"
            + "}")

    private const val removeCenterAboveADs = ("javascript:function removeCenterAboveADs() {"
            + "    var ad = document.getElementsById('_msk');"
            + "    ad.parentNode.removeChild(ad);"
            + "}")

    fun removeADs(view: WebView?, endCallback: FunctionNone?) {
        view ?: return

        var top = false
        var bottom = false
        var right = false
        var centerAbove = false

        BaseHtmlJSUtil.loadAndCallJs(
            view,
            removeTopADs,
            "removeTopADs",
            object : FunctionStringOne {
                override fun onCallBack(value: String?) {
                    top = true
                    callback(top, bottom, right, centerAbove, endCallback)
                }
            })

        BaseHtmlJSUtil.loadAndCallJs(
            view,
            removeBottomADs,
            "removeBottomADs",
            object : FunctionStringOne {
                override fun onCallBack(value: String?) {
                    bottom = true
                    callback(top, bottom, right, centerAbove, endCallback)
                }
            })

        BaseHtmlJSUtil.loadAndCallJs(
            view,
            removeRightADs,
            "removeRightADs",
            object : FunctionStringOne {
                override fun onCallBack(value: String?) {
                    right = true
                    callback(top, bottom, right, centerAbove, endCallback)
                }
            })

        BaseHtmlJSUtil.loadAndCallJs(
            view,
            removeCenterAboveADs,
            "removeCenterAboveADs",
            object : FunctionStringOne {
                override fun onCallBack(value: String?) {
                    centerAbove = true
                    callback(top, bottom, right, centerAbove, endCallback)
                }
            })

    }

    private fun callback(
        topSuccess: Boolean,
        bottomSuccess: Boolean,
        rightSuccess: Boolean,
        centerAbove: Boolean,
        endCallback: FunctionNone?
    ) {
        if (topSuccess && bottomSuccess && rightSuccess && centerAbove) {
            endCallback?.onCallBack()
        }
    }

}
