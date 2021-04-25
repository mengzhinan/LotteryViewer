package com.lotteryviewer.twocolorball.util

import com.lotteryviewer.base.app.BaseApplication
import com.lotteryviewer.base.util.RandomUtil
import com.lotteryviewer.twocolorball.R

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
    fun getNextRandomBlueBall(lastLastNum: Int, lastNum: Int): String {
        // 上一期的蓝色球方向
        val lastTowardsRight = lastLastNum <= lastNum
        // 根据数据分析，下一个号码大概率是反向的，即折线原理
        val isNowTowardsRight = if (RandomUtil.getRandomPercent(1)) {
            // 30% 概率不反向
            lastTowardsRight
        } else {
            // 70% 概率反向
            !lastTowardsRight
        }
        val ballArray = BaseApplication.get()?.resources?.getStringArray(R.array.blue_num_array)
            ?: return "$lastNum"


        val newBallArray = if (isNowTowardsRight) {
            ballArray.copyOfRange(lastNum, ballArray.size)
        } else {
            // 第一项是 --
            ballArray.copyOfRange(1, lastNum)
        }
        return newBallArray.random()
    }
}