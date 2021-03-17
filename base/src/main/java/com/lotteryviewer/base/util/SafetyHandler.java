package com.lotteryviewer.base.util;

import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;

/**
 * @Author: duke
 * @DateTime: 2021-03-17 12:42:42
 * @Description: 功能描述：
 */
public class SafetyHandler  extends Handler {
    private final WeakReference<Delegate> mWeakReference;


    private SafetyHandler() {
        this(null);
    }

    private SafetyHandler(SafetyHandler.Delegate delegate) {
        mWeakReference = new WeakReference<>(delegate);
    }

    public static SafetyHandler create(SafetyHandler.Delegate delegate) {
        return new SafetyHandler(delegate);
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        if (mWeakReference == null || mWeakReference.get() == null) {
            return;
        }

        mWeakReference.get().onReceivedHandlerMessage(msg);
    }

    public interface Delegate {
        void onReceivedHandlerMessage(Message message);
    }
}
