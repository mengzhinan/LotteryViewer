package com.lotteryviewer.base.util

import android.annotation.SuppressLint
import android.os.Build
import java.text.SimpleDateFormat
import java.util.*

/**
 * @Author: duke
 * @DateTime: 2021-03-17 12:47:40
 * @Description: 功能描述：
 */
object PhoneUtil {

    @SuppressLint("SimpleDateFormat")
    private val SDF = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

    fun getCurrentTimeStr(): String? {
        return try {
            SDF.format(Date(System.currentTimeMillis()))
        } catch (e: Exception) {
            null
        }
    }

    /**
     * 手机制造商
     * 如：Xiaomi
     */
    fun getPhoneManufacturer(): String? {
        return Build.MANUFACTURER
    }

    /**
     * 手机品牌
     * 如：Xiaomi
     */
    fun getPhoneBrand(): String? {
        return Build.BRAND
    }

    /**
     * 手机型号
     * 如：mi 9
     */
    fun getPhoneModel(): String? {
        return Build.MODEL
    }

    /**
     * 手机硬件
     * 如：qcom
     */
    fun getPhoneHardware(): String? {
        return Build.HARDWARE
    }

}
