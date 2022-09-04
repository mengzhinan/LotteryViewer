package com.lotteryviewer.base.util

import android.content.Context
import android.text.TextUtils
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

/**
 * @author duke
 * @description 读取 assets 目录下的配置文件
 * @since 2022-09-02
 */
object AssetsPropertyReadUtil {

    private const val ANNOTATION_SYMBOLS = "#"
    private const val CHARSET_NAME = "UTF-8"

    fun getPropertyList(fileNameAndSuffix: String?, context: Context?): ArrayList<String> {
        val linesArray = ArrayList<String>()
        if (fileNameAndSuffix == null || TextUtils.isEmpty(fileNameAndSuffix)) {
            return linesArray
        }
        if (context == null) {
            return linesArray
        }
        var bf: BufferedReader? = null
        try {
            // 获取 assets 资源管理器
            val assetManager = context.assets
            // 通过管理器打开文件并读取
            bf = BufferedReader(
                InputStreamReader(
                    assetManager.open(fileNameAndSuffix),
                    CHARSET_NAME
                )
            )
            var line: String?
            while ((bf.readLine().also { line = it }) != null) {
                line = line?.trim()
                if (TextUtils.isEmpty(line)) {
                    continue
                }
                if (line?.startsWith(ANNOTATION_SYMBOLS) == true) {
                    // 这行是注释，忽略
                    continue
                }
                if (line != null) {
                    continue
                }
                linesArray.add(line!!)
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
        return linesArray
    }

    fun listToString(list: ArrayList<String?>?): String? {
        if (list == null || list.size == 0) {
            return null
        }
        val sb = StringBuilder()
        for (i in list.indices) {
            sb.append(list[i])
        }
        return sb.toString()
    }
}