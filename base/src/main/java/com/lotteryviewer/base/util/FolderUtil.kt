package com.lotteryviewer.base.util

import android.content.Context
import android.os.Build
import android.os.Environment
import android.util.Log
import java.io.BufferedWriter
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStreamWriter

/**
 * @Author: duke
 * @DateTime: 2022-11-08 13:30:45
 * @Description: 功能描述：
 *
 * # /data/user/0/ <<等同于>> /data/data/
 * context.dataDir =          /data/user/0/com.xxx.aaaaaaaa，            canWrite = true
 * context.getDir(haha) =     /data/user/0/com.xxx.aaaaaaaa/app_haha，   canWrite = true
 * context.noBackupFilesDir = /data/user/0/com.xxx.aaaaaaaa/no_backup，  canWrite = true
 * context.filesDir =         /data/user/0/com.xxx.aaaaaaaa/files，      canWrite = true
 * context.cacheDir =         /data/user/0/com.xxx.aaaaaaaa/cache，      canWrite = true
 * context.codeCacheDir =     /data/user/0/com.xxx.aaaaaaaa/code_cache， canWrite = true
 *
 * # /storage/emulated/0/ <<等同于>> /sdcard/
 * context.externalCacheDir =                   /storage/emulated/0/Android/data/com.xxx.aaaaaaaa/cache，          canWrite = true
 * context.externalCacheDirs[0] =               /storage/emulated/0/Android/data/com.xxx.aaaaaaaa/cache，          canWrite = true
 * context.getExternalFilesDir(DOWNLOADS) =     /storage/emulated/0/Android/data/com.xxx.aaaaaaaa/files/Download， canWrite = true
 * context.getExternalFilesDirs(DOWNLOADS)[0] = /storage/emulated/0/Android/data/com.xxx.aaaaaaaa/files/Download， canWrite = true
 * context.obbDir =                             /storage/emulated/0/Android/obb/com.xxx.aaaaaaaa，                 canWrite = true
 * context.obbDirs[0] =                         /storage/emulated/0/Android/obb/com.xxx.aaaaaaaa，                 canWrite = true
 * context.externalMediaDirs[0] =               /storage/emulated/0/Android/media/com.xxx.aaaaaaaa，               canWrite = true
 *
 * # /storage/emulated/0/ <<等同于>> /sdcard/
 * Environment_getRootDirectory() =                           /system，                      canWrite = false
 * Environment_getDataDirectory() =                           /data，                        canWrite = false
 * Environment_getDownloadCacheDirectory() =                  /data/cache，                  canWrite = false
 * Environment_getStorageDirectory() =                        /storage，                     canWrite = false
 * Environment_getExternalStorageDirectory() =                /storage/emulated/0，          canWrite = false
 * Environment_getExternalStoragePublicDirectory(DOWNLOADS) = /storage/emulated/0/Download， canWrite = true
 *
 *
 */
object FolderUtil {

    /**
     * Folder: /data/data/<packageName>
     * CanWrite: true
     */
    fun getInternalDataDir(context: Context?): File? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            context?.dataDir
        } else {
            null
        }
    }

    /**
     * Folder: /data/data/<packageName>/app_<childFolderName>
     * CanWrite: true
     */
    fun getInternalDir(context: Context?, childFolderName: String?): File? {
        return context?.getDir(childFolderName, Context.MODE_PRIVATE)
    }

    /**
     * Folder: /data/data/<packageName>/no_backup
     * CanWrite: true
     */
    fun getInternalNoBackupFilesDir(context: Context?): File? {
        return context?.noBackupFilesDir
    }

    /**
     * Folder: /data/data/<packageName>/files
     * CanWrite: true
     */
    fun getInternalFilesDir(context: Context?): File? {
        return context?.filesDir
    }

    /**
     * Folder: /data/data/<packageName>/cache
     * CanWrite: true
     */
    fun getInternalCacheDir(context: Context?): File? {
        return context?.cacheDir
    }

    /**
     * Folder: /data/data/<packageName>/code_cache
     * CanWrite: true
     */
    fun getInternalCodeCacheDir(context: Context?): File? {
        return context?.codeCacheDir
    }

    /**
     * Folder: /sdcard/Android/data/<packageName>/cache
     * CanWrite: true
     */
    fun getExternalCacheDir(context: Context?): File? {
        return context?.externalCacheDir
    }

    /**
     * Folders: 同 getExternalCacheDir(context)
     * CanWrite: true
     */
    fun getExternalCacheDirs(context: Context?): Array<File>? {
        return context?.externalCacheDirs
    }

    /**
     * @param type 参考 Environment 类常量。如：{@link #android.os.Environment.DIRECTORY_DOWNLOADS}
     * Folder: /sdcard/Android/data/<packageName>/files/Download
     * CanWrite: true
     */
    fun getExternalFilesDir(context: Context?, type: String?): File? {
        return context?.getExternalFilesDir(type)
    }

    /**
     * @param type 参考 Environment 类常量。如：{@link #android.os.Environment.DIRECTORY_DOWNLOADS}
     * Folders: 同 getExternalFilesDir(context, type)
     * CanWrite: true
     */
    fun getExternalFilesDirs(context: Context?, type: String?): Array<File>? {
        return context?.getExternalFilesDirs(type)
    }

    /**
     * Folder: /sdcard/Android/obb/<packageName>
     * CanWrite: true
     */
    fun getExternalObbDir(context: Context?): File? {
        return context?.obbDir
    }

    /**
     * Folders: 同上 getExternalObbDir(context)
     * CanWrite: true
     */
    fun getExternalObbDirs(context: Context?): Array<File>? {
        return context?.obbDirs
    }

    /**
     * Folders: /sdcard/Android/media/<packageName>
     * CanWrite: true
     */
    @Deprecated("", ReplaceWith(""))
    fun getExternalMediaDirs(context: Context?): Array<File>? {
        return context?.externalMediaDirs
    }

    /**
     * Folders: /system
     * CanWrite: false
     */
    fun getEnvironmentRootDirectory(): File {
        return Environment.getRootDirectory()
    }

    /**
     * Folders: /data
     * CanWrite: false
     */
    fun getEnvironmentDataDirectory(): File {
        return Environment.getDataDirectory()
    }

    /**
     * Folders: /data/cache
     * CanWrite: false
     */
    fun getEnvironmentDownloadCacheDirectory(): File {
        return Environment.getDownloadCacheDirectory()
    }

    /**
     * Folders: /storage
     * CanWrite: false
     */
    fun getEnvironmentStorageDirectory(): File? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            Environment.getStorageDirectory()
        } else {
            null
        }
    }

    /**
     * Folders: /sdcard/
     * CanWrite: false
     */
    fun getEnvironmentExternalStorageDirectory(): File {
        return Environment.getExternalStorageDirectory()
    }

    /**
     * @param type 参考 Environment 类常量。如：{@link #android.os.Environment.DIRECTORY_DOWNLOADS}
     * Folders: /sdcard/Download
     * CanWrite: true
     */
    fun getEnvironmentExternalStoragePublicDirectory(type: String): File {
        return Environment.getExternalStoragePublicDirectory(type)
    }


    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ test below~~~~~~~~~~~~~~~~~~~~~~~~~

    fun testPrint(context: Context?) {
        context ?: return

        val dataDir = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            context.dataDir
        } else {
            null
        }
        val dataDirStatus = tryWriteFile(dataDir, "test_file1.txt", "test_aaa")
        testLog("context.dataDir = $dataDir， canWrite = $dataDirStatus")

        val getDir = context.getDir("haha", Context.MODE_PRIVATE)
        val getDirStatus = tryWriteFile(getDir, "test_file2.txt", "test_aaa")
        testLog("context.getDir(haha) = $getDir， canWrite = $getDirStatus")

        val noBackupFilesDir = context.noBackupFilesDir
        val noBackupFilesDirStatus = tryWriteFile(noBackupFilesDir, "test_file3.txt", "test_aaa")
        testLog("context.noBackupFilesDir = $noBackupFilesDir， canWrite = $noBackupFilesDirStatus")

        val filesDir = context.filesDir
        val filesDirStatus = tryWriteFile(filesDir, "test_file4.txt", "test_aaa")
        testLog("context.filesDir = $filesDir， canWrite = $filesDirStatus")

        val cacheDir = context.cacheDir
        val cacheDirStatus = tryWriteFile(cacheDir, "test_file5.txt", "test_aaa")
        testLog("context.cacheDir = $cacheDir， canWrite = $cacheDirStatus")

        val codeCacheDir = context.codeCacheDir
        val codeCacheDirStatus = tryWriteFile(codeCacheDir, "test_file6.txt", "test_aaa")
        testLog("context.codeCacheDir = $codeCacheDir， canWrite = $codeCacheDirStatus")


        val externalCacheDir = context.externalCacheDir
        val externalCacheDirStatus = tryWriteFile(externalCacheDir, "test_file7.txt", "test_aaa")
        testLog("context.externalCacheDir = $externalCacheDir， canWrite = $externalCacheDirStatus")

        val externalCacheDirs = context.externalCacheDirs[0]
        val externalCacheDirsStatus = tryWriteFile(externalCacheDirs, "test_file8.txt", "test_aaa")
        testLog("context.externalCacheDirs[0] = $externalCacheDirs， canWrite = $externalCacheDirsStatus")

        val getExternalFilesDir = context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)
        val getExternalFilesDirStatus =
            tryWriteFile(getExternalFilesDir, "test_file9.txt", "test_aaa")
        testLog("context.getExternalFilesDir(DOWNLOADS) = $getExternalFilesDir， canWrite = $getExternalFilesDirStatus")

        val getExternalFilesDirs = context.getExternalFilesDirs(Environment.DIRECTORY_DOWNLOADS)[0]
        val getExternalFilesDirsStatus =
            tryWriteFile(getExternalFilesDirs, "test_file10.txt", "test_aaa")
        testLog("context.getExternalFilesDirs(DOWNLOADS)[0] = $getExternalFilesDirs， canWrite = $getExternalFilesDirsStatus")

        val obbDir = context.obbDir
        val obbDirStatus = tryWriteFile(obbDir, "test_file11.txt", "test_aaa")
        testLog("context.obbDir = $obbDir， canWrite = $obbDirStatus")

        val obbDirs = context.obbDirs[0]
        val obbDirsStatus = tryWriteFile(obbDirs, "test_file12.txt", "test_aaa")
        testLog("context.obbDirs[0] = $obbDirs， canWrite = $obbDirsStatus")

        val externalMediaDirs = context.externalMediaDirs[0]
        val externalMediaDirsStatus = tryWriteFile(externalMediaDirs, "test_file13.txt", "test_aaa")
        testLog("context.externalMediaDirs[0] = $externalMediaDirs， canWrite = $externalMediaDirsStatus")


        val Environment_getRootDirectory = Environment.getRootDirectory()
        val Environment_getRootDirectoryStatus =
            tryWriteFile(Environment_getRootDirectory, "test_file14.txt", "test_aaa")
        testLog("Environment_getRootDirectory() = $Environment_getRootDirectory， canWrite = $Environment_getRootDirectoryStatus")

        val Environment_getDataDirectory = Environment.getDataDirectory()
        val Environment_getDataDirectoryStatus =
            tryWriteFile(Environment_getDataDirectory, "test_file15.txt", "test_aaa")
        testLog("Environment_getDataDirectory() = $Environment_getDataDirectory， canWrite = $Environment_getDataDirectoryStatus")

        val Environment_getDownloadCacheDirectory = Environment.getDownloadCacheDirectory()
        val Environment_getDownloadCacheDirectoryStatus =
            tryWriteFile(Environment_getDownloadCacheDirectory, "test_file16.txt", "test_aaa")
        testLog("Environment_getDownloadCacheDirectory() = $Environment_getDownloadCacheDirectory， canWrite = $Environment_getDownloadCacheDirectoryStatus")

        val Environment_getStorageDirectory = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            Environment.getStorageDirectory()
        } else {
            null
        }
        val Environment_getStorageDirectoryStatus =
            tryWriteFile(Environment_getStorageDirectory, "test_file17.txt", "test_aaa")
        testLog("Environment_getStorageDirectory() = $Environment_getStorageDirectory， canWrite = $Environment_getStorageDirectoryStatus")

        val Environment_getExternalStorageDirectory = Environment.getExternalStorageDirectory()
        val Environment_getExternalStorageDirectoryStatus =
            tryWriteFile(Environment_getExternalStorageDirectory, "test_file18.txt", "test_aaa")
        testLog("Environment_getExternalStorageDirectory() = $Environment_getExternalStorageDirectory， canWrite = $Environment_getExternalStorageDirectoryStatus")

        val Environment_getExternalStoragePublicDirectory =
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
        val Environment_getExternalStoragePublicDirectoryStatus = tryWriteFile(
            Environment_getExternalStoragePublicDirectory, "test_file19.txt", "test_aaa"
        )
        testLog("Environment_getExternalStoragePublicDirectory(DOWNLOADS) = $Environment_getExternalStoragePublicDirectory， canWrite = $Environment_getExternalStoragePublicDirectoryStatus")

    }

    private fun tryWriteFile(folder: File?, fileName: String, content: String): Boolean {
        var fos: FileOutputStream? = null
        var osw: OutputStreamWriter? = null
        var bw: BufferedWriter? = null
        try {
            if (folder == null || !folder.exists()) {
                folder?.mkdirs()
            }

            val targetFile = File(folder, fileName)
            if (!targetFile.exists()) {
                targetFile.createNewFile()
            }

            fos = FileOutputStream(targetFile)
            osw = OutputStreamWriter(fos)
            bw = BufferedWriter(osw)
            bw.write(content)
            bw.flush()
            return true
        } catch (e: Exception) {
            e.printStackTrace()
            // do nothing
        } finally {
            try {
                bw?.close()
            } catch (e: Exception) {
                e.printStackTrace()
                // do nothing
            }

            try {
                osw?.close()
            } catch (e: Exception) {
                e.printStackTrace()
                // do nothing
            }

            try {
                fos?.close()
            } catch (e: Exception) {
                e.printStackTrace()
                // do nothing
            }
        }
        return false
    }

    private fun testLog(msg: String) {
        Log.e(TAG, msg)
    }

    private const val TAG = "test_folder_r"

}
