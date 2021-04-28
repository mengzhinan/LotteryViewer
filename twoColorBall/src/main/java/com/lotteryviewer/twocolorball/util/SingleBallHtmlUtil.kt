package com.lotteryviewer.twocolorball.util

import android.util.Log
import android.webkit.WebView
import com.lotteryviewer.base.interfaces.FunctionNone
import com.lotteryviewer.base.interfaces.FunctionStringOne
import com.lotteryviewer.base.util.BaseHtmlJSUtil

/**
 * @Author: duke
 * @DateTime: 2021-03-24 17:13:10
 * @Description: 功能描述：
 *
 */
object SingleBallHtmlUtil {

    // 移除默认节点 UI
//    private const val demo = ("javascript:function deleteByTagName0() {"
//            + "var header = document.getElementsByTagName('header')[0];"
//            + "header.parentNode.removeChild(header);"
//            + "}")

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

    // 中奖城市
    private const val getHitPrizeCity = ("javascript:function getHitPrizeCity() {"
            + "var spanS = document.getElementsByClassName('ydjzc')[0];"
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
            + "nums += '${SingleBallDataUtil.SPLIT}';"
            + "}"
            + "return nums;"
            + "}")

    // 获取历史最近两期开奖号码的篮色球
    private var getTwoHistoryBlueNum = ("javascript:function getTwoHistoryBlueNum() {"
            + "var lqiuSpans = document.getElementsByClassName('lqiu');"
            + "var lqiuLast = lqiuSpans[0].innerText;"
            + "var lqiuLastLast = lqiuSpans[1].innerText;"
            + "var nums = lqiuLast + '${SingleBallDataUtil.SPLIT}' + lqiuLastLast;"
            + "return nums;"
            + "}")


    fun getHtmlText(view: WebView?, endCallback: FunctionNone?) {
        view ?: return
        Log.e(SingleBallDataUtil.TAG, "开始解析 html")

        var isSequenceOK = false
        var isDateOK = false
        var isPrizeNumOK = false
        var isPrizeCityOK = false
        var isTwoHistoryBlueOK = false

        BaseHtmlJSUtil.loadAndCallJs(
            view,
            getCurrentPrizeSequence,
            "getCurrentPrizeSequence",
            object : FunctionStringOne {
                override fun onCallBack(value: String?) {
                    SingleBallDataUtil.parseSequenceStr(value)
                    isSequenceOK = true
                    checkDateAndCallback(
                        isSequenceOK,
                        isDateOK,
                        isPrizeNumOK,
                        isPrizeCityOK,
                        isTwoHistoryBlueOK,
                        endCallback
                    )
                }
            })

        BaseHtmlJSUtil.loadAndCallJs(
            view,
            getCurrentPrizeDate,
            "getCurrentPrizeDate",
            object : FunctionStringOne {
                override fun onCallBack(value: String?) {
                    SingleBallDataUtil.parseDateStr(value)
                    isDateOK = true
                    checkDateAndCallback(
                        isSequenceOK,
                        isDateOK,
                        isPrizeNumOK,
                        isPrizeCityOK,
                        isTwoHistoryBlueOK,
                        endCallback
                    )
                }
            })

        BaseHtmlJSUtil.loadAndCallJs(
            view,
            getCurrentPrizeNums,
            "getCurrentPrizeNums",
            object : FunctionStringOne {
                override fun onCallBack(value: String?) {
                    SingleBallDataUtil.parsePrizeBallNum(value)
                    isPrizeNumOK = true
                    checkDateAndCallback(
                        isSequenceOK,
                        isDateOK,
                        isPrizeNumOK,
                        isPrizeCityOK,
                        isTwoHistoryBlueOK,
                        endCallback
                    )
                }
            })

        BaseHtmlJSUtil.loadAndCallJs(
            view,
            getHitPrizeCity,
            "getHitPrizeCity",
            object : FunctionStringOne {
                override fun onCallBack(value: String?) {
                    SingleBallDataUtil.parseCityStr(value)
                    isPrizeCityOK = true
                    checkDateAndCallback(
                        isSequenceOK,
                        isDateOK,
                        isPrizeNumOK,
                        isPrizeCityOK,
                        isTwoHistoryBlueOK,
                        endCallback
                    )
                }
            })

        BaseHtmlJSUtil.loadAndCallJs(
            view,
            getTwoHistoryBlueNum,
            "getTwoHistoryBlueNum",
            object : FunctionStringOne {
                override fun onCallBack(value: String?) {
                    SingleBallDataUtil.parseTwoHistoryBlueStr(value)
                    isTwoHistoryBlueOK = true
                    checkDateAndCallback(
                        isSequenceOK,
                        isDateOK,
                        isPrizeNumOK,
                        isPrizeCityOK,
                        isTwoHistoryBlueOK,
                        endCallback
                    )
                }
            })
    }

    private fun checkDateAndCallback(
        bl1: Boolean,
        bl2: Boolean,
        bl3: Boolean,
        bl4: Boolean,
        bl5: Boolean,
        callback: FunctionNone?
    ) {
        if (bl1 && bl2 && bl3 && bl4 && bl5) {
            callback?.onCallBack()
        }
    }

}
