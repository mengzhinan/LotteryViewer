package com.lotteryviewer.base.util

import android.app.Activity
import android.os.Environment
import android.util.Log

/**
 * @Author: duke
 * @DateTime: 2021-03-31 14:57:41
 * @Description: 功能描述：
 *

context?.codeCacheDir?.absolutePath = /data/user/0/com.test.androidr_file_test/code_cache

context?.noBackupFilesDir?.absolutePath = /data/user/0/com.test.androidr_file_test/no_backup

context?.cacheDir?.absolutePath = /data/user/0/com.test.androidr_file_test/cache
context?.filesDir?.absolutePath = /data/user/0/com.test.androidr_file_test/files

context?.externalCacheDir?.absolutePath = /storage/emulated/0/Android/data/com.test.androidr_file_test/cache
context?.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)?.absolutePath = /storage/emulated/0/Android/data/com.test.androidr_file_test/files/Download

context?.obbDir?.absolutePath = /storage/emulated/0/Android/obb/com.test.androidr_file_test

 */
object AndroidFilePath {

    private const val TAG = "test_file_path"

    fun getFilePath(context: Activity?) {

        Log.e(TAG, "context?.cacheDir?.absolutePath = ${context?.cacheDir?.absolutePath}")
        Log.e(TAG, "context?.codeCacheDir?.absolutePath = ${context?.codeCacheDir?.absolutePath}")
        Log.e(
            TAG,
            "context?.externalCacheDir?.absolutePath = ${context?.externalCacheDir?.absolutePath}"
        )
        Log.e(TAG, "context?.filesDir?.absolutePath = ${context?.filesDir?.absolutePath}")
        Log.e(
            TAG,
            "context?.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)?.absolutePath = ${
                context?.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)?.absolutePath
            }"
        )
        Log.e(
            TAG,
            "context?.noBackupFilesDir?.absolutePath = ${context?.noBackupFilesDir?.absolutePath}"
        )
        Log.e(TAG, "context?.obbDir?.absolutePath = ${context?.obbDir?.absolutePath}")


    }

}