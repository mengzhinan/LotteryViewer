package com.lotteryviewer.twocolorball.util

import android.util.Log
import android.webkit.WebView
import com.lotteryviewer.base.interfaces.FunctionStringOne
import com.lotteryviewer.base.util.BaseHtmlJSUtil

/**
 * @Author: duke
 * @DateTime: 2021-03-24 17:13:10
 * @Description: 功能描述： 解析历史号码，分析统计
 *
 */
object MultiBetBallHtmlUtil {

    // 爬取历史中奖号码组(开奖日期_红1_红2_红3_红4_红5_红6_蓝7;开奖日期_红1_红2_红3_红4_红5_红6_蓝7。。。)
    // js 代码之间不要添加注释，否则运行报错
    private const val getHistoryBallStr =
        ("javascript:function getHistoryBallStr() {"
                + "    var tbody = document.getElementsByTagName('tbody')[0];"
                + "    var trs = tbody.getElementsByTagName('tr');"
                + "    var totalCount = trs.length;"
                + "    var balls = '';"
                + "    for (var i = 0; i < totalCount; i++) {"
                + "        var tr = trs[i];"
                + "        var balls = balls + tr.getElementsByTagName('td')[1].innerText;"
                + "        var balls = balls + '${MultiBetBallDataUtil.SPLIT_INNER}';"
                + "        for (var j = 0; j < 7; j++) {"
                + "            balls = balls + tr.getElementsByTagName('span')[j].innerText;"
                + "            if (j < 6) {"
                + "                balls = balls + '${MultiBetBallDataUtil.SPLIT_INNER}';"
                + "            }"
                + "        }"
                + "        if (i < totalCount - 1) {"
                + "            balls = balls + '${MultiBetBallDataUtil.SPLIT_GAP}';"
                + "        }"
                + "    }"
                + "    return balls;"
                + "}")

    fun getHtmlText(view: WebView?, endCallback: FunctionStringOne?) {
        view ?: return
        Log.e(MultiBetBallDataUtil.TAG, "开始解析 html")

        BaseHtmlJSUtil.loadAndCallJs(
            view,
            getHistoryBallStr,
            "getHistoryBallStr",
            object : FunctionStringOne {
                override fun onCallBack(value: String?) {
                    MultiBetBallDataUtil.setBaseSourceData(value)
                    MultiBetBallDataUtil.parseHistoryBalls()
                    endCallback?.onCallBack(value)
                }
            })

    }

}
