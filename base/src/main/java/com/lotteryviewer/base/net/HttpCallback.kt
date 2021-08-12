package com.lotteryviewer.base.net

/**
 * @Author: duke
 * @DateTime: 2021-08-10 13:58:06
 * @Description: 功能描述：
 *
 */
interface HttpCallback {

    fun onSuccess(result: String?, responseCode: Int = 0, responseMessage: String? = null)

    fun onFailure(error: String?, responseCode: Int = 0, responseMessage: String? = null)

}