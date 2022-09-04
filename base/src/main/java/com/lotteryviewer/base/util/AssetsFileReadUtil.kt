package com.lotteryviewer.base.util

import android.content.Context
import android.text.TextUtils
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

/**
 * @author duke
 * @description 读取 assets 目录下的文本文件
 * @since 2022-09-02
 */
object AssetsFileReadUtil {

    private const val CHARSET_NAME = "UTF-8"

    fun getString(fileNameAndSuffix: String?, context: Context?): String {
        val stringBuilder: StringBuilder = StringBuilder()
        if (fileNameAndSuffix == null || TextUtils.isEmpty(fileNameAndSuffix)) {
            return stringBuilder.toString()
        }
        if (context == null) {
            return stringBuilder.toString()
        }

        var bf: BufferedReader? = null
        try {
            // 获取 assets 资源管理器
            val assetManager = context.assets
            // 通过管理器打开文件并读取
            bf = BufferedReader(
                InputStreamReader(
                    assetManager.open(
                        fileNameAndSuffix
                    ), CHARSET_NAME
                )
            )
            var line: String?
            while (bf.readLine().also { line = it } != null) {
                line = line?.trim()
                if (line == null || TextUtils.isEmpty(line)) {
                    continue
                }
                stringBuilder.append(line)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            if (bf != null) {
                try {
                    bf.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
        return stringBuilder.toString()
    }

}
