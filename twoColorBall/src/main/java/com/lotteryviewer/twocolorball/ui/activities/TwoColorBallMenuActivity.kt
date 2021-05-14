package com.lotteryviewer.twocolorball.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton
import com.lotteryviewer.base.ui.BaseActivity
import com.lotteryviewer.twocolorball.R
import com.lotteryviewer.twocolorball.ui.activities.calculator.TwoColorBallCalculatorActivity
import com.lotteryviewer.twocolorball.ui.activities.chart.TwoColorBallChartActivity
import com.lotteryviewer.twocolorball.ui.activities.history.TwoColorBallHistoryBallActivity
import com.lotteryviewer.twocolorball.ui.activities.main.TwoColorBallMainPageActivity

/**
 * 双色球 app 菜单 页面
 */
class TwoColorBallMenuActivity : BaseActivity() {

    private var twoColorBallMain: AppCompatButton? = null
    private var twoColorBallCalculator: AppCompatButton? = null
    private var twoColorBallHistory: AppCompatButton? = null
    private var twoColorBallChart: AppCompatButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_two_color_menu)

        setActionBarTitle(R.string.two_color_ball_title)

        twoColorBallMain = findViewById(R.id.btn_two_color_ball_main)
        twoColorBallCalculator = findViewById(R.id.btn_two_color_ball_calculator)
        twoColorBallHistory = findViewById(R.id.btn_two_color_ball_history)
        twoColorBallChart = findViewById(R.id.btn_two_color_ball_chart)

        twoColorBallMain?.setOnClickListener {
            startPage(TwoColorBallMainPageActivity::class.java)
        }

        twoColorBallCalculator?.setOnClickListener {
            startPage(TwoColorBallCalculatorActivity::class.java)
        }

        twoColorBallHistory?.setOnClickListener {
            startPage(TwoColorBallHistoryBallActivity::class.java)
        }

        twoColorBallChart?.setOnClickListener {
            startPage(TwoColorBallChartActivity::class.java)
        }
    }

    private fun startPage(clazz: Class<*>) {
        startActivity(Intent(this@TwoColorBallMenuActivity, clazz))
    }

}
