package com.lotteryviewer.twocolorball.util

import android.util.Log
import com.lotteryviewer.base.util.TextUtil
import com.lotteryviewer.twocolorball.ui.model.BallInfo

/**
 * @Author: duke
 * @DateTime: 2021-03-24 17:28:00
 * @Description: 功能描述：历史开奖信息统计数据
 *
 */
object HistoryBallDataUtil {

    const val TAG = "history_ball_js"

    // 每组号码内部的分割线
    const val SPLIT_INNER = "_"

    // 每组之间的分割线，区分组与组的标志
    const val SPLIT_GAP = ";"

    // 总共解析了多少组开奖号码
    private var groupCount: Int = 0

    // js 解析的原始数据字符串(备用)
    private var baseSourceData: String? = ""

    // 最近一期开奖号码是
    private var recentPrizeNumStr: String? = ""

    // 上期和上上期篮球号码
    private var lastBlueNum: Int = 0
    private var lastLastBlueNum: Int = 0

    // 统计红球、篮球出现次数分布集合(按照 号码 顺序 排序)
    private var redBallNumOrderList: List<BallInfo> = List(33) { BallInfo() }
    private var blueBallNumOrderList: List<BallInfo> = List(16) { BallInfo() }

    // 统计红球、篮球出现次数分布集合(按照 出现次数 倒序 排序)
    private var redBallAppearOrderList: List<BallInfo> = List(33) { BallInfo() }
    private var blueBallAppearOrderList: List<BallInfo> = List(16) { BallInfo() }

    fun getGroupCount(): Int {
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

    fun setBaseSourceData(data: String?) {
        baseSourceData = data
    }

    fun getRedBallNumOrderList(): List<BallInfo> {
        return redBallNumOrderList
    }

    fun getBlueBallNumOrderList(): List<BallInfo> {
        return blueBallNumOrderList
    }

    fun getRedBallAppearOrderList(): List<BallInfo> {
        return redBallAppearOrderList
    }

    fun getBlueBallAppearOrderList(): List<BallInfo> {
        return blueBallAppearOrderList
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
        for (index in redBallNumOrderList.indices) {
            redBallNumOrderList[index].ballNum = index + 1
            redBallNumOrderList[index].appearCount = 0
        }
        // 初始化蓝球 list
        for (index in blueBallNumOrderList.indices) {
            blueBallNumOrderList[index].ballNum = index + 1
            blueBallNumOrderList[index].appearCount = 0
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
                    val ballInfo = blueBallNumOrderList[jInt - 1]
                    ballInfo.ballNum = jInt
                    ballInfo.appearCount += 1
                } else {
                    // 红色球
                    val jInt = TextUtil.parseToInt(itemArr[j], -1)
                    if (jInt == -1) {
                        continue
                    }
                    val ballInfo = redBallNumOrderList[jInt - 1]
                    ballInfo.ballNum = jInt
                    ballInfo.appearCount += 1
                }
            }
        }

        // 倒叙排序，统计出现次数
        redBallAppearOrderList = redBallNumOrderList.sortedByDescending { it.appearCount }
        blueBallAppearOrderList = blueBallNumOrderList.sortedByDescending { it.appearCount }

        Log.e(TAG, "数据统计完毕")

    }

}
