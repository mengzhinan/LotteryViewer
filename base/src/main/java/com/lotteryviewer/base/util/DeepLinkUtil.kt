package com.lotteryviewer.base.util

import android.content.Intent
import android.net.Uri
import com.lotteryviewer.base.app.BaseApplication

/**
 * @Author: duke
 * @DateTime: 2021-04-07 14:58:13
 * @Description: 功能描述：DeepLink 调起
 *
 */
object DeepLinkUtil {

    interface OnIntentParamCallback {

        fun putParam(intent: Intent)

    }

    fun openDeepLink(deepLinkUrl: String?, callback: OnIntentParamCallback?): Boolean {
        return try {
            val context = BaseApplication.get() ?: return false
            val intent = Intent()
            callback?.putParam(intent)
            intent.data = Uri.parse(deepLinkUrl)
            intent.action = Intent.ACTION_VIEW
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.addCategory(Intent.CATEGORY_DEFAULT)
            // intent.addCategory(Intent.CATEGORY_BROWSABLE)
            context.startActivity(intent)
            // 调起成功，返回 true 告诉应用内的 webView 框架不要处理后续操作
            true
        } catch (e: Exception) {
            // 调起失败，继续应用内的其他操作
            false
        }
    }

}