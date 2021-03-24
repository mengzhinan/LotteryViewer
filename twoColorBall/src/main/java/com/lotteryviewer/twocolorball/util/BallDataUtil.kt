package com.lotteryviewer.twocolorball.util

import com.lotteryviewer.twocolorball.R

/**
 * @Author: duke
 * @DateTime: 2021-03-24 17:28:00
 * @Description: 功能描述：
 *
 */
object BallDataUtil {

    const val TAG = "two_color_ball_js"

    // 号码球之间的分割符
    const val SPLIT = "lvSplit"

    var prizeSequenceStr: String? = ""
    var prizeDateStr: String? = ""
    var prizeNumStr: String? = ""

    val prizeNumArray: Array<String> = arrayOf("?", "?", "?", "?", "?", "?", "?")

    fun parseStr() {
        prizeSequenceStr = prizeSequenceStr?.trim()
        prizeSequenceStr = prizeSequenceStr?.replace("\"", "")

        prizeDateStr = prizeDateStr?.trim()
        prizeDateStr = prizeDateStr?.replace("\"", "")
    }

    // 把解析到的字符串号码，转换为对应的 int 数组
    fun parseBallNumArray(): Boolean {
        try {
            prizeNumStr = prizeNumStr?.trim()

            // 去除两端的双引号
            prizeNumStr = prizeNumStr?.replace("\"", "")

            // 去除开头的分隔符
            if (prizeNumStr?.startsWith(SPLIT) == true) {
                prizeNumStr = prizeNumStr?.substring(SPLIT.length)
            }
            val l = prizeNumStr?.length ?: 0
            if (l == 0 || l <= SPLIT.length) {
                return false
            }

            // 去除末尾的分隔符
            if (prizeNumStr?.endsWith(SPLIT) == true) {
                prizeNumStr = prizeNumStr?.substring(0, l - SPLIT.length)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }

        val tempArray = prizeNumStr?.split(SPLIT) ?: return false
        val tempSize = tempArray.size
        if (tempSize != prizeNumArray.size) {
            return false
        }
        for (index in 0 until tempSize) {
            try {
                // 检测号码是否是数字
                tempArray[index].trim().toInt()
                prizeNumArray[index] = tempArray[index]
            } catch (e: Exception) {
                e.printStackTrace()
                return false
            }
        }
        return true
    }


    fun getPrizeTipText(redCount: Int, blueCount: Int): Int {
        if (blueCount == 1 && (redCount in 0..2)) {
            return R.string.result_tip_6
        } else if ((redCount == 4 && blueCount == 0) || (redCount == 3 && blueCount == 1)) {
            return R.string.result_tip_5
        } else if ((redCount == 5 && blueCount == 0) || (redCount == 4 && blueCount == 1)) {
            return R.string.result_tip_4
        } else if (redCount == 5 && blueCount == 1) {
            return R.string.result_tip_3
        } else if (redCount == 6 && blueCount == 0) {
            return R.string.result_tip_2
        } else if (redCount == 6 && blueCount == 1) {
            return R.string.result_tip_1
        } else {
            return R.string.result_tip_empty
        }
    }

}
