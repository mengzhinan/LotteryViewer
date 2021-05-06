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
    private var redBallScatterList: List<BallInfo> = List(33) { BallInfo() }
    private var blueBallScatterList: List<BallInfo> = List(16) { BallInfo() }

    fun getGroupNum(): Int {
        return groupCount
    }

    fun getRecentPrizeNumStr(): String? {
        return recentPrizeNumStr
    }

    fun getLastLastBallNum(): Int {
        return lastLastBlueNum
    }

    fun getLastBallNum(): Int {
        return lastBlueNum
    }

    private var baseSourceData: String? = ""

    fun setBaseSourceData(data: String?) {
        baseSourceData = data
    }

    fun getRedBallScatterList(): List<BallInfo> {
        return redBallScatterList
    }

    fun getBlueBallScatterList(): List<BallInfo> {
        return blueBallScatterList
    }

    /**
     * 解析历史开奖数据
     * @param parseItemCount 需要解析多少天的数据. -1 全部解析
     */
    fun parseHistoryBalls(parseItemCount: Int = -1) {
        if (TextUtil.isNullOrEmpty(baseSourceData)) {
            return
        }

        // 初始化红球 list
        for (index in redBallScatterList.indices) {
            redBallScatterList[index].ballNum = index + 1
            redBallScatterList[index].appearCount = 0
        }
        // 初始化蓝球 list
        for (index in blueBallScatterList.indices) {
            blueBallScatterList[index].ballNum = index + 1
            blueBallScatterList[index].appearCount = 0
        }

        var newValue = baseSourceData?.trim()
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

            if (parseItemCount > 0 && index > parseItemCount - 1) {
                // 控制只解析多少天的数据
                break
            }

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
                    val ballInfo = blueBallScatterList[jInt - 1]
                    ballInfo.ballNum = jInt
                    ballInfo.appearCount += 1
                } else {
                    // 红色球
                    val jInt = TextUtil.parseToInt(itemArr[j], -1)
                    if (jInt == -1) {
                        continue
                    }
                    val ballInfo = redBallScatterList[jInt - 1]
                    ballInfo.ballNum = jInt
                    ballInfo.appearCount += 1
                }
            }
        }

        // 倒叙排序
        blueBallScatterList = blueBallScatterList.sortedByDescending { it.appearCount }
        redBallScatterList = redBallScatterList.sortedByDescending { it.appearCount }

        Log.e(TAG, "数据统计完毕")

    }

}
