package com.lotteryviewer.base.util

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.net.Uri

/**
 * author: duke
 * version: 1.0
 * dateTime: 2021-03-18 22:54
 * description:
 *
 */
object ClipManagerHelper {

    fun saveToClip(context: Context?, text: String?) {
        context ?: return
        text ?: return
        val contentResolver = context.contentResolver ?: return
        val clipboardManager =
            context.getSystemService(Context.CLIPBOARD_SERVICE) as? ClipboardManager ?: return
        val newClipData = ClipData.newUri(contentResolver, "", Uri.parse(text))
        clipboardManager.setPrimaryClip(newClipData)
    }

    fun getFromClip(context: Context?): CharSequence? {
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