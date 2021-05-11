package com.lotteryviewer.twocolorball.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton
import com.lotteryviewer.base.ui.BaseActivity
import com.lotteryviewer.twocolorball.R

class TwoColorBallMenuActivity : BaseActivity() {

    private var twoColorBallMain: AppCompatButton? = null
    private var twoColorBallHistory: AppCompatButton? = null
    private var twoColorBallCalculator: AppCompatButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_two_color_menu)

        setActionBarTitle(R.string.two_color_ball_title)

        twoColorBallMain = findViewById(R.id.btn_two_color_ball_main)
        twoColorBallHistory = findViewById(R.id.btn_two_color_ball_history)
        twoColorBallCalculator = findViewById(R.id.btn_two_color_ball_calculator)

        twoColorBallMain?.setOnClickListener {
            startPage(TwoColorBallPageActivity::class.java)
        }

        twoColorBallHistory?.setOnClickListener {
            startPage(HistoryBallActivity::class.java)
        }

        twoColorBallCalculator?.setOnClickListener {
            startPage(TwoColorBallCalculatorActivity::class.java)
        }
    }

    private fun startPage(clazz: Class<*>) {
        startActivity(Intent(this@TwoColorBallMenuActivity, clazz))
    }

}
