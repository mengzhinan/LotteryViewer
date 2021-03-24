package com.lotteryviewer.base.util

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.net.Uri

/**
 * author: duke
 * version: 1.0
 * dateTime: 2021-03-18 22:54
 * description: 支持 text 和 content://
 * 注意： content:// 通过文本方式在剪切板读写时，程序会崩溃
 *
 */
object ClipManagerUtil {

    fun saveToClip(context: Context?, text: String?) {
        context ?: return
        text ?: return
        val contentResolver = context.contentResolver ?: return
        val clipboardManager =
            context.getSystemService(Context.CLIPBOARD_SERVICE) as? ClipboardManager ?: return
        val newClipData = ClipData.newUri(contentResolver, "", Uri.parse(text))
        clipboardManager.setPrimaryClip(newClipData)
    }

    fun getFromClip(context: Context?): String? {
        context ?: return null
        val clipboardManager =
            context.getSystemService(Context.CLIPBOARD_SERVICE) as? ClipboardManager ?: return null
        val clipData = clipboardManager.primaryClip ?: return null
        if (clipData.itemCount <= 0) {
            return null
        }
        return clipData.getItemAt(0)?.uri?.toString()
    }

}