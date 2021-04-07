package com.lotteryviewer.base.util

/**
 * @Author: duke
 * @DateTime: 2021-04-07 14:48:09
 * @Description: 功能描述：Android 系统自带 Emoji 表情图片字符
 *
 *  http://www.unicode.org/emoji/charts/full-emoji-list.html
 *
 *  1、获取 编码组 对应的表情 字符
 *  2、设置到 TextView.text 属性中即可显示表情图片
 *  3、设置 TextView.textSize 属性可调整图片大小
 *  4、设置 TextView.textColor 属性可调整图片的透明度问题，比喻设置为黑色解决图片半透明问题。
 */
object SystemEmojiUtil {

    /**
     * 获取复合类型编码的表情字符
     * For EP: mutableListOf(0x1F468, 0x200D, 0x1F4BB)
     */
    fun getSystemEmojiTexts(list: MutableList<Int>?): String {
        list ?: return ""
        if (list.size == 1) {
            return getSystemEmojiText(list[0])
        }
        var str = ""
        for (index in list.indices) {
            str += getSystemEmojiText(list[index])
        }
        return str
    }

    /**
     * 获取系统表情文本，设置到 textView 中后自动展示为图片
     * For EP: 彩虹 🌈 的编码 codePoint = 0x1F308
     */
    fun getSystemEmojiText(codePoint: Int): String {
        return try {
            String(Character.toChars(codePoint))
        } catch (e: Exception) {
            e.printStackTrace()
            ""
        }
    }

}
