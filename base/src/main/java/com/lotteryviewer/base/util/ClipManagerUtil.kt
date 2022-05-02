package com.lotteryviewer.base.util

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.net.Uri
import android.os.Build

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

    private fun getClipboardManager(context: Context?): ClipboardManager? {
        return try {
            context?.getSystemService(Context.CLIPBOARD_SERVICE) as? ClipboardManager?
        } catch (e: Exception) {
            null
        }
    }

    private fun getClipboardPrimaryClip(context: Context?): ClipData? {
        return try {
            getClipboardManager(context)?.primaryClip
        } catch (e: Exception) {
            null
        }
    }

    /**
     * 读取剪切板内容
     */
    fun getPrimaryClipText(context: Context?): String? {
        val primaryClip = getClipboardPrimaryClip(context) ?: return null
        if (primaryClip.itemCount <= 0) {
            return null
        }
        val charSequence = primaryClip.getItemAt(0)?.text ?: return null
        return charSequence.toString()
    }

    /**
     * 设置剪切板
     */
    fun setClipText(context: Context?, text: String?) {
        text ?: return
        val clipboard = getClipboardManager(context)
        clipboard?.setPrimaryClip(ClipData.newPlainText(text, text))
    }

    /**
     * 清空剪切板
     */
    fun clearClipText(context: Context?) {
        val clipboard = getClipboardManager(context)
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                clipboard?.clearPrimaryClip()
            } else {
                clipboard?.setPrimaryClip(ClipData.newPlainText("", ""))
            }
        } catch (e: Exception) {
        }
    }

}