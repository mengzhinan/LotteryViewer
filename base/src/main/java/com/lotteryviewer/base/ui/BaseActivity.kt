package com.lotteryviewer.base.ui

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initActionBar()
    }

    override fun onResume() {
        super.onResume()
        hideOrHideStatusBar()
        hideOrShowActionBar()
    }

    protected open fun isHideActionBar(): Boolean {
        return false
    }

    protected open fun isTransparentActionBar(): Boolean {
        return false
    }

    protected open fun isHideStatusBar(): Boolean {
        return false
    }

    private fun hideOrShowActionBar() {
        if (isTransparentActionBar()) {
            supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
        if (isHideActionBar()) {
            supportActionBar?.hide()
        } else {
            supportActionBar?.show()
        }
    }

    private fun hideOrHideStatusBar() {
        if (isHideStatusBar()) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
            window.statusBarColor = Color.TRANSPARENT
            window.decorView.fitsSystemWindows = true
        }
    }

    private fun initActionBar() {
        // 返回按钮
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        // 是否显示标题
        supportActionBar?.setDisplayShowTitleEnabled(true)
    }

    protected fun setActionBarTitle(@StringRes titleResId: Int?) {
        titleResId ?: return
        setActionBarTitle(getString(titleResId))
    }

    protected fun setActionBarTitle(title: String?) {
        title ?: return
        supportActionBar?.title = title
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

}
