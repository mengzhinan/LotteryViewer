package com.lotteryviewer.sunset.util

import android.webkit.WebView
import com.lotteryviewer.base.interfaces.FunctionNone
import com.lotteryviewer.base.interfaces.FunctionStringOne
import com.lotteryviewer.base.util.BaseHtmlJSUtil

/**
 * @Author: duke
 * @DateTime: 2021-03-30 11:20:32
 * @Description: 功能描述：
 *
 */
object SunsetHtmlUtil {

    // 获取日出日落等信息
    private var getSunInfo = ("javascript:function getSunInfo() {"
            + "var rootTable = document.getElementsById('richurimo_table');"
            + "var rootTbody = rootTable.getElementsByTagName('tbody')[0];"
            + "var rootTr = rootTbody.getElementsByTagName('tr')[0];"
            + "var rootTd = rootTr.getElementsByTagName('td')[0];"
            + "var subTable = rootTd.getElementsByTagName('table')[0];"
            + "var subTbody = rootTd.getElementsByTagName('tbody')[0];"
            + "var subTrArray = rootTd.getElementsByTagName('tr');"
            + "var subTrSize = subTrArray.length;"
            + "var finalResult = '';"
            + "for(var i = 0; i < subTrSize; i++) {"
            + "     var tds = subTrArray[i].getElementsByTagName('td');"
            + "     var dateStr = tds[0].innerText.split(' ')[0];"
            + "     if (dateStr == '${SunsetDataUtil.CURRENT_DATE_YYYY_MM_DD}'){"
            + "         finalResult += tds[1];"
            + "         finalResult += ${SunsetDataUtil.SPLIT};"
            + "         finalResult += tds[2];"
            + "         finalResult += ${SunsetDataUtil.SPLIT};"
            + "         finalResult += tds[3];"
            + "         finalResult += ${SunsetDataUtil.SPLIT};"
            + "         finalResult += tds[4];"
            + "         finalResult += ${SunsetDataUtil.SPLIT};"
            + "         finalResult += tds[5];"
            + "         finalResult += ${SunsetDataUtil.SPLIT};"
            + "         finalResult += tds[6];"
            + "         break;"
            + "     }"
            + "}"
            + "return finalResult;"
            + "}")


    fun getHtmlText(view: WebView?, endCallback: FunctionNone?) {
        view ?: return

        BaseHtmlJSUtil.loadAndCallJs(
            view,
            getSunInfo,
            "getSunInfo",
            object : FunctionStringOne {
                override fun onCallBack(value: String?) {
                    SunsetDataUtil.parseData(value)
                    endCallback?.onCallBack()
                }
            })
    }

}
