package com.lotteryviewer.home.ui.activities

import android.content.Intent
import android.os.Bundle
import android.os.Message
import androidx.appcompat.app.AppCompatActivity
import com.lotteryviewer.base.interfaces.FunctionNone
import com.lotteryviewer.base.util.SafetyHandler
import com.lotteryviewer.base.util.WindowInsetsControllerUtil
import com.lotteryviewer.home.R
import com.lotteryviewer.home.ui.dialog.DisclaimerDialogUtil

class SplashActivity : AppCompatActivity(), SafetyHandler.Delegate {

    companion object {
        private const val HANDLER_WHAT_FINISH = 1
        private const val DELAY_TIME = 500L
    }

    private var safetyHandler: SafetyHandler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 全屏
        WindowInsetsControllerUtil.setFullScreen(this)

        setContentView(R.layout.activity_splash)

        safetyHandler = SafetyHandler.create(this)

    }

    override fun onResume() {
        super.onResume()
        val isSuccessShow = DisclaimerDialogUtil.showDisclaimerDialog(this, object : FunctionNone {
            override fun onCallBack() {
                safetyHandler?.sendEmptyMessageDelayed(HANDLER_WHAT_FINISH, 0)
            }
        })
        if (!isSuccessShow) {
            safetyHandler?.sendEmptyMessageDelayed(HANDLER_WHAT_FINISH, DELAY_TIME)
        }
    }

    override fun onReceivedHandlerMessage(message: Message?) {
        if (message?.what == HANDLER_WHAT_FINISH) {
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            finish()
        }
    }

}
