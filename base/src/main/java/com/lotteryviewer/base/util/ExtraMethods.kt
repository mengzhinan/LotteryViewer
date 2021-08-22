package com.lotteryviewer.base.util

import android.app.Activity
import android.content.Context
import android.content.Intent

/**
 * Author: duke
 * DateTime: 2021-08-22 22-01
 * Description: 功能说明
 *
 */

inline fun <reified A : Activity> Context.startActivity() {
    val intent = Intent(this, A::class.java)
    startActivity(intent)
}

inline fun <reified A : Activity> Context.startActivity(intentInvoke: (i: Intent) -> Unit) {
    val intent = Intent(this, A::class.java)
//    intentInvoke(intent)
    intentInvoke.invoke(intent)
    startActivity(intent)
}
