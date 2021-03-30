package com.lotteryviewer.base.util

/**
 * @Author: duke
 * @DateTime: 2021-03-17 12:41:12
 * @Description: 功能描述：
 */
object TextUtil {

    fun isNullOrEmpty(str: String?): Boolean {
        return str == null || str.trim().isEmpty()
    }

    fun parseToInt(text: String?, defaultValue: Int): Int {
        return if (text == null) {
            defaultValue
        } else try {
            text.toInt()
        } catch (e: Exception) {
            defaultValue
        }
    }

    fun isAllUpperCaseLetter(s: String?): Boolean {
        if (s == null) {
            return false
        }
        val chars = s.toCharArray()
        for (i in chars.indices) {
            if (!isUpperCaseLetter(chars[i])) {
                return false
            }
        }
        return true
    }

    fun isAllLowerCaseLetter(s: String?): Boolean {
        if (s == null) {
            return false
        }
        val chars = s.toCharArray()
        for (i in chars.indices) {
            if (!isLowerCaseLetter(chars[i])) {
                return false
            }
        }
        return true
    }

    fun isAllIntegralNumber(s: String?): Boolean {
        if (s == null) {
            return false
        }
        val chars = s.toCharArray()
        for (i in chars.indices) {
            if (!isIntegralNumber(chars[i])) {
                return false
            }
        }
        return true
    }

    fun isUpperCaseLetter(c: Char?): Boolean {
        return if (c == null) {
            false
        } else {
            c.toInt() in 65..90
        }
    }

    fun isLowerCaseLetter(c: Char?): Boolean {
        return if (c == null) {
            false
        } else {
            c.toInt() in 97..122
        }
    }

    fun isIntegralNumber(c: Char?): Boolean {
        return if (c == null) {
            false
        } else {
            c.toInt() in 48..57
        }
    }

}