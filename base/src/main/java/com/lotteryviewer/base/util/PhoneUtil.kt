package com.lotteryviewer.base.util

import android.annotation.SuppressLint
import android.os.Build
import java.text.SimpleDateFormat
import java.util.*

/**
 * Author: duke
 * Created: 2022-05-02 17:53:39
 * Description: usage
 * 10 ultra
 * E/sdf: 1 BOARD = cas
 * E/sdf: 2 DEVICE = cas
 * E/sdf: 3 PRODUCT = cas
 * E/sdf: 4 MODEL = M2007J1SC
 *
 * 11_lite
 * E/sdf: 1 BOARD = renoir
 * E/sdf: 2 DEVICE = renoir
 * E/sdf: 3 PRODUCT = renoir
 * E/sdf: 4 MODEL = M2101K9C
 *
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

    fun isMI10_Ultra(): Boolean {
        return Build.BOARD.lowercase(Locale.getDefault()) == "cas"
                || Build.DEVICE.lowercase(Locale.getDefault()) == "cas"
                || Build.PRODUCT.lowercase(Locale.getDefault()) == "cas"
                || Build.MODEL.lowercase(Locale.getDefault()) == "m2007j1sc"
    }

    fun isMI11_Lite(): Boolean {
        return Build.BOARD.lowercase(Locale.getDefault()) == "renoir"
                || Build.DEVICE.lowercase(Locale.getDefault()) == "renoir"
                || Build.PRODUCT.lowercase(Locale.getDefault()) == "renoir"
                || Build.MODEL.lowercase(Locale.getDefault()) == "m2101k9c"
    }

}
