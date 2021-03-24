package com.lotteryviewer.base.util

/**
 * @Author: duke
 * @DateTime: 2021-03-24 13:34:07
 * @Description: 功能描述：随机抽取特定个数 item 返回
 *
 */
object RandomUtil {

    /**
     * 给定数组，随机获取其中 count 个 items 返回
     * @param itemArrays 给定的数组
     * @param count 需要获取个数
     */
    fun getRandomItems(itemArrays: Array<String>?, count: Int): MutableList<String>? {
        return getRandomItems(
            if (itemArrays.isNullOrEmpty()) {
                null
            } else {
                mutableListOf(*itemArrays)
            }, count
        )
    }

    fun getRandomItems(itemList: MutableList<String>?, count: Int): MutableList<String>? {
        if (itemList.isNullOrEmpty() || count <= 0 || count > itemList.size) {
            return null
        }
        val tempList = mutableListOf<String>()
        tempList.addAll(itemList)
        val resultList = mutableListOf<String>()
        var i = 0
        while (true) {
            val tempListSize = tempList.size
            if (tempListSize == 0) {
                break
            }
            if (i >= count) {
                break
            }
            val value = tempList.removeAt((0 until tempListSize).random())
            resultList.add(value)
            i++
        }
        return resultList
    }

}