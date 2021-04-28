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
    private const val getHistoryBallStr =
        ("javascript:function getHistoryBallStr() {"
                + "    // 直接获取 tbody 标签"
                + "    var tbody = document.getElementsByTagName('tbody')[0];"
                + "    // 找到 tbody 下所有的 trs 数组"
                + "    var trs = tbody.getElementsByTagName('tr');"
                + "    // 计算有多少行数据，确定要循环多少次"
                + "    var totalCount = trs.length;"
                + "    // 存储结果的变量"
                + "    var balls = '';"
                + "    for (var i = 0; i < totalCount; i++) {"
                + "        // 循环每一行，"
                + "        var tr = trs[i];"
                + "        // 得到每一次开奖日期"
                + "        var balls = balls + tr.getElementsByTagName('td')[1].innerText;"
                + "        var balls = balls + '${MultiBetBallDataUtil.SPLIT_INNER}';"
                + "        for (var j = 0; j < 7; j++) {"
                + "            // 累加每行的前 7 个 span 内的文本，即 6 个红球和 1 个篮球字符串"
                + "            balls = balls + tr.getElementsByTagName('span')[j].innerText;"
                + "            if (j < 6) {"
                + "                // 每一组的 7 个球之间使用 _ 下划线分割，最后一个号码除外"
                + "                balls = balls + '${MultiBetBallDataUtil.SPLIT_INNER}';"
                + "            }"
                + "        }"
                + "        // 每一组之间使用 ; 分号分割"
                + "        balls = balls + '${MultiBetBallDataUtil.SPLIT_GAP}';"
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
                    MultiBetBallDataUtil.parseHistoryBalls(value)
                    endCallback?.onCallBack(value)
                }
            })

    }

}
