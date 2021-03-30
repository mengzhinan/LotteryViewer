package com.lotteryviewer.sunset.util

import android.content.Intent
import java.text.SimpleDateFormat
import java.util.*

/**
 * @Author: duke
 * @DateTime: 2021-03-30 11:10:12
 * @Description: 功能描述：
 *
 */
object SunsetDataUtil {

    const val PARAM_URL_TYPE = "param_url_type"
    const val URL_BEIJING = "https://richurimo.bmcx.com/beijing__richurimo/"
    const val URL_WUHAN = "https://richurimo.bmcx.com/wuhan__richurimo/"
    const val URL_HUANGGANG = "https://richurimo.bmcx.com/huanggang__richurimo/"

    const val SPLIT = "split"

    fun getURLByCity(cityPinYin: String): String {
        return "https://richurimo.bmcx.com/${cityPinYin}__richurimo/"
    }

    fun parseIntentUrl(intent: Intent?): String {
        return intent?.getStringExtra(PARAM_URL_TYPE) ?: URL_BEIJING
    }

    // 2021年03月01日
    val CURRENT_DATE_YYYY_MM_DD: String = SimpleDateFormat("yyyy年MM月dd日").format(Date())

    // 日期，日出，日中，日落，昼长，天亮，天黑
    private var dateStr: String? = ""
    private var sunriseStr: String? = ""
    private var sunMiddleStr: String? = ""
    private var sunsetStr: String? = ""
    private var dayLengthStr: String? = ""
    private var dayLightStr: String? = ""
    private var dayDarkStr: String? = ""

    fun getDateStr(): String {
        return dateStr ?: ""
    }

    fun parseData(value: String?) {
        value ?: return

    }

}
