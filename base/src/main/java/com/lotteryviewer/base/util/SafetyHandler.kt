package com.lotteryviewer.base.util

import android.os.Handler
import android.os.Looper
import android.os.Message
import java.lang.ref.WeakReference

/**
 * @Author: duke
 * @DateTime: 2021-03-17 12:42:42
 * @Description: 功能描述：
 */
class SafetyHandler private constructor(delegate: Delegate? = null) :
    Handler(Looper.getMainLooper()) {

    companion object {
        fun create(delegate: Delegate?): SafetyHandler {
            return SafetyHandler(delegate)
        }
    }

    private val mWeakReference: WeakReference<Delegate?>?

    init {
        mWeakReference = WeakReference(delegate)
    }

    override fun handleMessage(msg: Message) {
        super.handleMessage(msg)
        mWeakReference?.get()?.onReceivedHandlerMessage(msg)
    }

    interface Delegate {
        fun onReceivedHandlerMessage(message: Message?)
    }

}