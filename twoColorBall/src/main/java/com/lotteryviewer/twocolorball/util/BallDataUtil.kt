package com.lotteryviewer.twocolorball.util

import com.lotteryviewer.base.util.TextUtil
import com.lotteryviewer.twocolorball.R
import java.util.*

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

    private var prizeSequenceStr: String? = ""
    private var prizeDateStr: String? = ""
    private var prizeNumStr: String? = ""

    fun getPrizeSequenceStr(): String {
        return prizeSequenceStr ?: ""
    }

    fun getPrizeDateStr(): String {
        return prizeDateStr ?: ""
    }

    val prizeNumArray: Array<String> = arrayOf("?", "?", "?", "?", "?", "?", "?")

    /**
     * 开奖号码数据是否有效
     */
    fun isPrizeNumArrayValid(): Boolean {
        for (index in prizeNumArray.indices) {
            if (prizeNumArray[index] == "?") {
                // 主要发现开奖号码数组内有一个问号，即表示数据解析失败，数据无效
                return false
            }
        }
        return true
    }

    fun copyArray(srcArr: Array<String>?, destArr: Array<String>?) {
        srcArr ?: return
        destArr ?: return
        System.arraycopy(srcArr, 0, destArr, 0, srcArr.size)
    }

    fun parseSequenceStr(value: String?) {
        if (TextUtil.isNullOrEmpty(value)) {
            prizeSequenceStr = ""
            return
        }
        prizeSequenceStr = prizeSequenceStr?.trim()
        prizeSequenceStr = prizeSequenceStr?.toLowerCase(Locale.getDefault())
        prizeSequenceStr = prizeSequenceStr?.replace("\"", "")
        prizeSequenceStr = prizeSequenceStr?.replace("null", "")
    }

    fun parseDateStr(value: String?) {
        if (TextUtil.isNullOrEmpty(value)) {
            prizeDateStr = ""
            return
        }
        prizeDateStr = prizeDateStr?.trim()
        prizeDateStr = prizeDateStr?.toLowerCase(Locale.getDefault())
        prizeDateStr = prizeDateStr?.replace("\"", "")
        prizeDateStr = prizeDateStr?.replace("null", "")
    }

    /**
     * 把解析到的开奖号码字符串，转换为对应的 String 数组
     */
    fun parsePrizeBallNum(value: String?) {
        try {
            prizeNumStr = value
            prizeNumStr = prizeNumStr?.trim()

            // 不要 lower case，否则字符串 split 会失败
            // 其实里面也不会大、小写字母
//            prizeNumStr = prizeNumStr?.toLowerCase(Locale.getDefault())

            prizeNumStr = prizeNumStr?.replace("\"", "")
            prizeNumStr = prizeNumStr?.replace("null", "")

            // 去除开头的分隔符
            if (prizeNumStr?.startsWith(SPLIT) == true) {
                prizeNumStr = prizeNumStr?.substring(SPLIT.length)
            }

            val l = prizeNumStr?.length ?: 0
            if (l == 0 || l <= SPLIT.length) {
                // 已经是无效的数据
                prizeNumStr = ""
                return
            }

            // 去除末尾的分隔符
            if (prizeNumStr?.endsWith(SPLIT) == true) {
                prizeNumStr = prizeNumStr?.substring(0, l - SPLIT.length)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return
        }

        val tempArray = prizeNumStr?.split(SPLIT)
        val tempSize = tempArray?.size ?: 0
        if (tempSize != prizeNumArray.size) {
            prizeNumStr = ""
            return
        }
        for (index in 0 until tempSize) {
            try {
                val item = tempArray?.get(index) ?: continue

                // 检测号码是否是数字
                item.trim().toInt()

                // 上一步转换为数字 OK，数据有效。保存数据
                prizeNumArray[index] = item
            } catch (e: Exception) {
                e.printStackTrace()
                return
            }
        }
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
