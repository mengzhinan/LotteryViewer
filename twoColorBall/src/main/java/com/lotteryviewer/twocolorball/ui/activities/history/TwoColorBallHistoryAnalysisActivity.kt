package com.lotteryviewer.twocolorball.ui.activities.history

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.lotteryviewer.base.app.BaseApplication
import com.lotteryviewer.base.ui.BaseActivity
import com.lotteryviewer.twocolorball.R
import com.lotteryviewer.twocolorball.ui.widget.LabelTextLayout
import com.lotteryviewer.twocolorball.util.BlueBallRandomUtil
import com.lotteryviewer.twocolorball.util.HistoryBallDataUtil

/**
 * 双色球 历史开奖 数据统计
 */
class TwoColorBallHistoryAnalysisActivity : BaseActivity() {

    // 最近开奖号码
    private var ltlRecentPrizeNum: LabelTextLayout? = null

    // 推荐的下一期篮球号码
    private var ltlRecommendBlueNum: LabelTextLayout? = null

    // 获取的历史开奖信息组数(获取了多少期开奖信息)
    private var ltlGroupSize: LabelTextLayout? = null

    // 红球号码顺序数据
    private var tvRedBallNumOrder: TextView? = null

    // 篮球号码顺序数据
    private var tvRedBallAppearOrder: TextView? = null

    // 红球号码出现次数数据
    private var tvBlueBallNumOrder: TextView? = null

    // 篮球号码出现次数数据
    private var tvBlueBallAppearOrder: TextView? = null

    private val blueBallCount = 16
    private val blueBallArray: Array<String> = Array(blueBallCount) { "0" }

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
        ltlRecentPrizeNum = findViewById(R.id.ltl_recent_prize_num)
        ltlRecommendBlueNum = findViewById(R.id.ltl_recommend_blue_num)
        ltlGroupSize = findViewById(R.id.ltl_group_size)
        tvRedBallNumOrder = findViewById(R.id.tv_red_ball_num_order)
        tvRedBallAppearOrder = findViewById(R.id.tv_red_ball_appear_order)
        tvBlueBallNumOrder = findViewById(R.id.tv_blue_ball_num_order)
        tvBlueBallAppearOrder = findViewById(R.id.tv_blue_ball_appear_order)
    }

    private fun setupData() {
        // 获取 stringArray 里面的蓝色球数组(第一个是 "--")
        val ballArray: Array<String>? = resources?.getStringArray(R.array.blue_num_array)
        if (ballArray != null && ballArray.size > 1) {
            System.arraycopy(ballArray, 1, blueBallArray, 0, blueBallCount)
        }

        ltlRecentPrizeNum?.setLabel("最近开奖号码：")
        ltlRecentPrizeNum?.setContent(HistoryBallDataUtil.getRecentPrizeNumStr())

        ltlRecommendBlueNum?.setTextSize(40f)
        ltlRecommendBlueNum?.setLabel("推荐篮色球：")
        ltlRecommendBlueNum?.setContent(
            BlueBallRandomUtil.getNextRandomBlueBall(
                blueBallArray,
                HistoryBallDataUtil.getLastLastBallNum(),
                HistoryBallDataUtil.getLastBallNum()
            )
        )
        ltlRecommendBlueNum?.setOnClickListener {
            ltlRecommendBlueNum?.setContent(
                BlueBallRandomUtil.getNextRandomBlueBall(
                    blueBallArray,
                    HistoryBallDataUtil.getLastLastBallNum(),
                    HistoryBallDataUtil.getLastBallNum()
                )
            )
            Toast.makeText(BaseApplication.get(), "重新推测篮球", Toast.LENGTH_SHORT).show()
        }

        ltlGroupSize?.setTextSize(20f)
        ltlGroupSize?.setLabel("解析历史数据数：")
        ltlGroupSize?.setContent(HistoryBallDataUtil.getGroupCount().toString())


        val redNumOrderList = HistoryBallDataUtil.getRedBallNumOrderList()
        var redNumOrderStr = ""
        for (index in redNumOrderList.indices) {
            val item = redNumOrderList[index]
            redNumOrderStr += "${getTwoSizeStr(item.ballNum)} -> ${item.appearCount}"
            redNumOrderStr += "\n"
        }
        tvRedBallNumOrder?.text = redNumOrderStr

        val redAppearOrderList = HistoryBallDataUtil.getRedBallAppearOrderList()
        var redAppearOrderStr = ""
        for (index in redAppearOrderList.indices) {
            val item = redAppearOrderList[index]
            redAppearOrderStr += "${getTwoSizeStr(item.ballNum)} -> ${item.appearCount}"
            redAppearOrderStr += "\n"
        }
        tvRedBallAppearOrder?.text = redAppearOrderStr

        val blueNumOrderList = HistoryBallDataUtil.getBlueBallNumOrderList()
        var blueNumOrderStr = ""
        for (index in blueNumOrderList.indices) {
            val item = blueNumOrderList[index]
            blueNumOrderStr += "${getTwoSizeStr(item.ballNum)} -> ${item.appearCount}"
            blueNumOrderStr += "\n"
        }
        tvBlueBallNumOrder?.text = blueNumOrderStr

        val blueAppearOrderList = HistoryBallDataUtil.getBlueBallAppearOrderList()
        var blueAppearOrderStr = ""
        for (index in blueAppearOrderList.indices) {
            val item = blueAppearOrderList[index]
            blueAppearOrderStr += "${getTwoSizeStr(item.ballNum)} -> ${item.appearCount}"
            blueAppearOrderStr += "\n"
        }
        tvBlueBallAppearOrder?.text = blueAppearOrderStr
    }

    /**
     * 数值小于 10 时，在前面补充 0
     */
    private fun getTwoSizeStr(i: Int): String {
        return if (i >= 10) {
            "$i"
        } else {
            "0$i"
        }
    }

}
