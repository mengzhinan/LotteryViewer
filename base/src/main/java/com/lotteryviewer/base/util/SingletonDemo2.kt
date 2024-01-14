package com.example.test2024

/**
 * Author: duke
 * DateTime: 2024-01-14 21:33:21
 * Description: usage
 * kotlin 懒加载单例模式
 */
class SingletonDemo2 private constructor() {

    companion object {
        val instance: SingletonDemo2 by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            SingletonDemo2()
        }
    }

}

fun main() {
    SingletonDemo2.instance
    SingletonDemo2.instance
    SingletonDemo2.instance
}