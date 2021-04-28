package com.lotteryviewer.twocolorball.util

import com.lotteryviewer.base.util.TextUtil

/**
 * @Author: duke
 * @DateTime: 2021-03-24 17:28:00
 * @Description: 功能描述：
 *
 */
object MultiBetBallDataUtil {

    const val TAG = "history_ball_js"

    // 每组号码内部的分割线
    const val SPLIT_INNER = "_"

    // 每组之间的分割线，区分组与组的标志
    const val SPLIT_GAP = ";"

    private var groupCount: String? = ""
    private var recentPrizeNumStr:String?=""
    private var recentPrizeDateStr: String? = ""



    fun parseHistoryBalls(historyBall: String?) {

    }

    val prizeNumArray: Array<String> = arrayOf("?", "?", "?", "?", "?", "?", "?")

    /**
     * 获取历史篮球号码(last _ lastLast)
     */
    fun parseTwoHistoryBlueStr(value: String?) {
        if (TextUtil.isNullOrEmpty(value)) {
            return
        }
        var newValue = value?.trim()
//        newValue = newValue?.toLowerCase(Locale.getDefault())
        newValue = newValue?.replace("\"", "")
        newValue = newValue?.replace("，", ",")
        newValue = newValue?.replace("null", "")

        val arr = newValue?.split(SPLIT_INNER)
        if (arr == null || arr.size != 2) {
            return
        }
    }

}
