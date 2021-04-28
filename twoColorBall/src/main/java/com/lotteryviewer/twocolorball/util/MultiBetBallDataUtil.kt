package com.lotteryviewer.twocolorball.util

import android.util.Log
import com.lotteryviewer.base.util.TextUtil
import com.lotteryviewer.twocolorball.ui.model.BallInfo

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

    private var groupCount: Int = 0
    private var recentPrizeNumStr: String? = ""
    private var lastLastBlueNum: Int = 0
    private var lastBlueNum: Int = 0

    private var redBallList: List<BallInfo> = List(33) { BallInfo() }
    private var blueBallList: List<BallInfo> = List(16) { BallInfo() }

    fun parseHistoryBalls(historyBallStr: String?) {
        if (TextUtil.isNullOrEmpty(historyBallStr)) {
            return
        }
        var newValue = historyBallStr?.trim()
        newValue = newValue?.replace("\"", "")
        newValue = newValue?.replace("，", ",")
        newValue = newValue?.replace("null", "")

        val groupArr = newValue?.split(SPLIT_GAP)
        if (groupArr == null || groupArr.isEmpty()) {
            return
        }
        groupCount = groupArr.size
        recentPrizeNumStr = groupArr[0]
        for (index in groupArr.indices) {
            val groupItem = groupArr[index]
            // 开奖日期_红1_红2_红3_红4_红5_红6_蓝7
            val itemArr = groupItem.split(SPLIT_INNER)
            if (itemArr.isEmpty() || itemArr.size < 7) {
                continue
            }
            if (index == 0) {
                lastBlueNum = TextUtil.parseToInt(itemArr[7], 0)
            } else if (index == 1) {
                lastLastBlueNum = TextUtil.parseToInt(itemArr[7], 0)
            }
            for (j in itemArr.indices) {
                if (j == 0) {
                    // 日期
                    continue
                } else if (j == 7) {
                    //篮色球
                    val jInt = TextUtil.parseToInt(itemArr[7], -1)
                    if (jInt == -1) {
                        continue
                    }
                    val ballInfo = blueBallList[jInt]
                    ballInfo.appearCount += 1
                } else {
                    // 红色球
                    val jInt = TextUtil.parseToInt(itemArr[j], -1)
                    if (jInt == -1) {
                        continue
                    }
                    val ballInfo = redBallList[jInt]
                    ballInfo.appearCount += 1
                }
            }
        }
        Log.e(TAG, "数据统计完毕")
    }

}
