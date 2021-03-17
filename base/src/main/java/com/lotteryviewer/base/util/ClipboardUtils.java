package com.lotteryviewer.base.util;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;

/**
 * @Author: duke
 * @DateTime: 2021-03-17 12:22:20
 * @Description: 功能描述：
 */
public class ClipboardUtils {

    public static void copyString(final Context pContext, final String pString) {
        ClipboardManager clipboardManager = (ClipboardManager) pContext.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clipData = ClipData.newPlainText("", pString);
        clipboardManager.setPrimaryClip(clipData);
    }

}
