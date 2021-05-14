package com.lotteryviewer.twocolorball.ui.activities.history

import android.os.Bundle
import com.lotteryviewer.base.ui.BaseActivity
import com.lotteryviewer.twocolorball.R

/**
 * 双色球 历史开奖 数据统计
 */
class TwoColorBallHistoryAnalysisActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history_analysis)

        initActionBar()
        initUI()
        setupData()
    }


    private fun initActionBar() {
        // 返回按钮
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        // 是否显示标题
        supportActionBar?.setDisplayShowTitleEnabled(true)
        // 设置标题
        supportActionBar?.title = getString(R.string.history_ball_analysis_title)
    }

    private fun initUI() {

    }

    private fun setupData() {

    }

}
