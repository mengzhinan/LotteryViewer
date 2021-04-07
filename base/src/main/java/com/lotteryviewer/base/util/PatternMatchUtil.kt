package com.lotteryviewer.base.util

import java.util.regex.Pattern

/**
 * @Author: duke
 * @DateTime: 2021-04-07 15:29:16
 * @Description: 功能描述：
 *
 */
object PatternMatchUtil {

    /**
     * 匹配字符串
     * @param str 待匹配的字符串
     * @param pattern 匹配模式，Pattern 类型。
     * 比喻: Pattern.compile("^http(s)?://m\\.weibo\\.cn/detail/(\\w+)(/)?")
     */
    private fun isMatchPatternStr(str: String?, pattern: Pattern?): Boolean {
        str ?: return false
        pattern ?: return false
        return pattern.matcher(str).matches()
    }

    /**
     * @param str 待匹配的字符串
     * @param pattern 匹配模式，Pattern 类型。
     * 比喻: Pattern.compile("^http(s)?://m\\.weibo\\.cn/detail/(\\w+)(/)?")
     * @param midIndex 组索引值，从 1 开始。group(0) 表示整个字符串本身
     * @return 匹配到的 midIndex 组的值
     */
    private fun getMatchPatternValue(str: String, pattern: Pattern, groupIndex: Int): String? {
        if (!isMatchPatternStr(str, pattern)) {
            return null
        }
        val matcher = pattern.matcher(str)
        if (matcher.groupCount() < groupIndex) {
            return null
        }
        // group(0) 标示整个字符串本身
        return try {
            matcher.group(groupIndex) ?: ""
        } catch (e: Exception) {
            ""
        }
    }

}