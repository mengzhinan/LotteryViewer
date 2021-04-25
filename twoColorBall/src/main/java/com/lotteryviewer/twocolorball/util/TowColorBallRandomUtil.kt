package com.lotteryviewer.twocolorball.util

import com.lotteryviewer.base.util.RandomUtil

/**
 * @Author: duke
 * @DateTime: 2021-04-23 17:11:46
 * @Description: 功能描述：
 *
 */
object TowColorBallRandomUtil {

    /**
     * 预测下一个篮色球
     */
    fun getNextRandomBlueBall(blueArray: Array<String>, lastLastNum: Int, lastNum: Int): String {
        // 上一期的蓝色球方向
        val lastTowardsRight = lastLastNum <= lastNum
        // 根据数据分析，下一个号码大概率是反向的，即折线原理
        val isNowTowardsRight = if (RandomUtil.getRandomPercent(1)) {
            // 10% 概率不反向
            lastTowardsRight
        } else {
            // 90% 概率反向
            !lastTowardsRight
        }


        val newBallArray = if (isNowTowardsRight) {
            blueArray.copyOfRange(lastNum, blueArray.size)
        } else {
            blueArray.copyOfRange(0, lastNum)
        }
        return newBallArray.random()
    }
}