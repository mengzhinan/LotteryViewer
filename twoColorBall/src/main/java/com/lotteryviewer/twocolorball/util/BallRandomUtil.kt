package com.lotteryviewer.twocolorball.util

import kotlin.random.Random

/**
 * @Author: duke
 * @DateTime: 2021-09-10 12:20:05
 * @Description: 功能描述：
 *
 */
object BallRandomUtil {

    private val BASE_SET_RED = hashSetOf(
        1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11,
        12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22,
        23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33
    )

    private val BASE_SET_BLUE = hashSetOf(
        1, 2, 3, 4, 5, 6, 7, 8,
        9, 10, 11, 12, 13, 14, 15, 16
    )


    fun getListRed(): List<Int> {
        val list = arrayListOf<Int>()
        var size = 0
        while (size < 6) {
            val random = Random.nextInt(BASE_SET_RED.size)
            val element = BASE_SET_RED.elementAt(random)
            if (list.contains(element)) {
                continue
            }
            list.add(element)
            size++
        }
        list.sort()
        return list
    }

    fun getBlue(): Int {
        return BASE_SET_BLUE.elementAt(Random.nextInt(BASE_SET_BLUE.size))
    }

}