package com.lotteryviewer.twocolorball.util

import android.util.Log
import android.webkit.WebView
import com.lotteryviewer.base.interfaces.FunctionNone
import com.lotteryviewer.base.interfaces.FunctionStringOne
import com.lotteryviewer.base.util.BaseHtmlJSUtil
import java.util.*

/**
 * @Author: duke
 * @DateTime: 2021-03-24 17:13:10
 * @Description: 功能描述：
 *
 */
object BallHtmlUtil {

    // 移除默认节点 UI
    private const val demo = ("javascript:function deleteByTagName0() {"
            + "var header = document.getElementsByTagName('header')[0];"
            + "header.parentNode.removeChild(header);"
            + "}")

    // 开奖期数
    private const val getCurrentPrizeSequence =
        ("javascript:function getCurrentPrizeSequence() {"
                + "return document.getElementsByTagName('select')[0].value;"
                + "}")

    // 开奖日期
    private const val getCurrentPrizeDate = ("javascript:function getCurrentPrizeDate() {"
            + "var divS = document.getElementsByClassName('kjrq')[0];"
            + "var spanS = divS.getElementsByTagName('span')[0];"
            + "return spanS.innerText;"
            + "}")

    // 获取本期头奖号码
    private var getCurrentPrizeNums = ("javascript:function getCurrentPrizeNums() {"
            + "var ul = document.getElementsByClassName('hmj')[0];"
            + "var lis = ul.getElementsByTagName('li');"
            + "var size = lis.length;"
            + "var nums = '';"
            + "for(var i = 0; i < size; i++) {"
            + "nums += lis[i].innerText;"
            + "nums += '${BallDataUtil.SPLIT}';"
            + "}"
            + "return nums;"
            + "}")


    fun getHtmlText(view: WebView?, endCallback: FunctionNone?) {
        view ?: return
        endCallback ?: return
        Log.e(BallDataUtil.TAG, "开始解析 html")

        var isSequenceOK = false
        var isDateOK = false
        var isPrizeNumOK = false

        BaseHtmlJSUtil.loadAndCallJs(
            view,
            getCurrentPrizeSequence,
            "getCurrentPrizeSequence",
            object : FunctionStringOne {
                override fun onCallBack(value: String?) {
                    BallDataUtil.prizeSequenceStr = value
                    if (BallDataUtil.prizeSequenceStr?.toLowerCase(Locale.getDefault()) == "null") {
                        BallDataUtil.prizeSequenceStr = "?"
                    }
                    BallDataUtil.parseStr()
                    isSequenceOK = true
                    checkDateAndCallback(isSequenceOK, isDateOK, isPrizeNumOK, endCallback)
                }
            })

        BaseHtmlJSUtil.loadAndCallJs(
            view,
            getCurrentPrizeDate,
            "getCurrentPrizeDate",
            object : FunctionStringOne {
                override fun onCallBack(value: String?) {
                    BallDataUtil.prizeDateStr = value
                    if (BallDataUtil.prizeDateStr?.toLowerCase(Locale.getDefault()) == "null") {
                        BallDataUtil.prizeDateStr = "?"
                    }
                    BallDataUtil.parseStr()
                    isDateOK = true
                    checkDateAndCallback(isSequenceOK, isDateOK, isPrizeNumOK, endCallback)
                }
            })

        BaseHtmlJSUtil.loadAndCallJs(
            view,
            getCurrentPrizeNums,
            "getCurrentPrizeNums",
            object : FunctionStringOne {
                override fun onCallBack(value: String?) {
                    BallDataUtil.prizeNumStr = value
                    isPrizeNumOK = true
                    checkDateAndCallback(isSequenceOK, isDateOK, isPrizeNumOK, endCallback)
                }
            })
    }

    private fun checkDateAndCallback(
        bl1: Boolean,
        bl2: Boolean,
        bl3: Boolean,
        callback: FunctionNone?
    ) {
        Log.e(
            BallDataUtil.TAG,
            "准备回调 bl1 = $bl1, bl2 = $bl2, bl3 = $bl3"
        )
        if (bl1 && bl2 && bl3) {
            callback?.onCallBack()
        }
    }

}