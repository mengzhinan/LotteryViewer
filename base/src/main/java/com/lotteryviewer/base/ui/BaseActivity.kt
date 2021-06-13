package com.lotteryviewer.base.ui

import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import androidx.annotation.ColorInt
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import com.lotteryviewer.base.R

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

    protected fun setActionBarBackIconColor(@ColorInt colorInt: Int) {
        val backDrawable = ResourcesCompat.getDrawable(resources, R.mipmap.icon_back_arrow, null)
        backDrawable?.colorFilter = PorterDuffColorFilter(colorInt, PorterDuff.Mode.SRC_IN)
        supportActionBar?.setHomeAsUpIndicator(backDrawable)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}
