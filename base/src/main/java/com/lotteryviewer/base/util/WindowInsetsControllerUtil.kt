package com.lotteryviewer.base.util

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat

/**
 * author: duke
 * version: 1.0
 * dateTime: 2021-06-05 20:29
 * description: window 控制
 *
 */
object WindowInsetsControllerUtil {

    fun getWindowInsetsController(activity: Activity?): WindowInsetsControllerCompat? {
        val decorView = activity?.window?.decorView
        decorView ?: return null
        return ViewCompat.getWindowInsetsController(decorView)
    }

    /**
     * 显示状态栏
     */
    fun showStatusBar(activity: Activity?) {
        getWindowInsetsController(activity)?.show(WindowInsetsCompat.Type.statusBars())
    }

    /**
     * 隐藏状态栏
     */
    fun hideStatusBar(activity: Activity?) {
        getWindowInsetsController(activity)?.hide(WindowInsetsCompat.Type.statusBars())
    }

    /**
     * 状态栏文字颜色改为黑色
     */
    fun setStatusBarTextColorBlack(activity: Activity?) {
        getWindowInsetsController(activity)?.isAppearanceLightStatusBars = true
    }

    /**
     * 状态栏文字颜色改为白色
     */
    fun setStatusBarTextColorWhite(activity: Activity?) {
        getWindowInsetsController(activity)?.isAppearanceLightStatusBars = false
    }

    /**
     * 显示导航栏
     */
    fun showNavigationBar(activity: Activity?) {
        getWindowInsetsController(activity)?.show(WindowInsetsCompat.Type.navigationBars())
    }

    /**
     * 隐藏导航栏
     */
    fun hideNavigationBar(activity: Activity?) {
        getWindowInsetsController(activity)?.hide(WindowInsetsCompat.Type.navigationBars())
    }

    /**
     * 显示输入法键盘
     */
    fun showIMEKeyboard(activity: Activity?) {
        getWindowInsetsController(activity)?.show(WindowInsetsCompat.Type.ime())
    }

    /**
     * 隐藏输入法键盘
     */
    fun hideIMEKeyboard(activity: Activity?) {
        getWindowInsetsController(activity)?.hide(WindowInsetsCompat.Type.ime())
    }

    /**
     * 设置全屏显示
     */
    fun setFullScreen(activity: Activity?) {
        if (activity is AppCompatActivity) {
            activity.supportActionBar?.hide()
        }
        getWindowInsetsController(activity)?.hide(WindowInsetsCompat.Type.systemBars())
        activity?.window?.decorView?.fitsSystemWindows = true
        hideIMEKeyboard(activity)
        hideStatusBar(activity)
        hideNavigationBar(activity)
    }

}
