package com.lotteryviewer.twocolorball.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatSpinner
import androidx.core.content.ContextCompat
import com.lotteryviewer.base.ui.BaseActivity
import com.lotteryviewer.twocolorball.R
import com.lotteryviewer.twocolorball.util.TwoColorBallDataUtil
import com.lotteryviewer.twocolorball.widget.BallLayout

class TwoColorBallCheckActivity : BaseActivity() {

    private var tvHitCity: TextView? = null
    private var tvSequence: TextView? = null
    private var tvDate: TextView? = null
    private var prizeNumsLayout: BallLayout? = null
    private var myNumsLayout: BallLayout? = null
    private var tvResult: TextView? = null

    // 进入此页面时，开奖号码已经解析到，而不是 ？
    private val finalBallArray = Array(TwoColorBallDataUtil.prizeNumArray.size) { "" }

    private val inputBallArray: Array<String> = arrayOf("?", "?", "?", "?", "?", "?", "?")

    private var spinner1: AppCompatSpinner? = null
    private var spinner2: AppCompatSpinner? = null
    private var spinner3: AppCompatSpinner? = null
    private var spinner4: AppCompatSpinner? = null
    private var spinner5: AppCompatSpinner? = null
    private var spinner6: AppCompatSpinner? = null
    private var spinner7: AppCompatSpinner? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_prize)

        if (TwoColorBallDataUtil.isPrizeNumArrayValid()) {
            // 获取解析到的数据，不要直接引用数组内存地址
            TwoColorBallDataUtil.copyArray(TwoColorBallDataUtil.prizeNumArray, finalBallArray)
        } else {
            Toast.makeText(
                this,
                getString(R.string.two_color_ball_prize_num_invalid),
                Toast.LENGTH_SHORT
            ).show()
        }

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
        supportActionBar?.title = getString(R.string.check_prize_title)
    }

    private fun initUI() {
        tvHitCity = findViewById(R.id.tv_hit_city)
        tvSequence = findViewById(R.id.tv_sequence)
        tvDate = findViewById(R.id.tv_date)
        prizeNumsLayout = findViewById(R.id.current_ball_layout)
        myNumsLayout = findViewById(R.id.your_ball_layout)
        tvResult = findViewById(R.id.result_text)
        spinner1 = findViewById(R.id.input_spinner1)
        spinner2 = findViewById(R.id.input_spinner2)
        spinner3 = findViewById(R.id.input_spinner3)
        spinner4 = findViewById(R.id.input_spinner4)
        spinner5 = findViewById(R.id.input_spinner5)
        spinner6 = findViewById(R.id.input_spinner6)
        spinner7 = findViewById(R.id.input_spinner7)
        spinner1?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                setUserSelectData(0, parent, position)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }
        spinner2?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                setUserSelectData(1, parent, position)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }
        spinner3?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                setUserSelectData(2, parent, position)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }
        spinner4?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                setUserSelectData(3, parent, position)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }
        spinner5?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                setUserSelectData(4, parent, position)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }
        spinner6?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                setUserSelectData(5, parent, position)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }
        spinner7?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                setUserSelectData(6, parent, position)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }
    }

    private fun setUserSelectData(spinnerIndex: Int, parent: AdapterView<*>?, position: Int) {
        // 可能是 --
        var value = parent?.adapter?.getItem(position) as? String
        value = value?.trim()
        if (value == null) {
            return
        }
        try {
            value.toInt()
        } catch (e: Exception) {
            // 不是数字
            return
        }
        inputBallArray[spinnerIndex] = value
        myNumsLayout?.setBalls(inputBallArray, finalBallArray)

        // 其实此处不用 delay，只是 post 即可
        // 需要 post 等到 myNumsLayout.onLayout() 方法重绘完毕
        // 才能拿到正确的中奖号码个数
        myNumsLayout?.postDelayed({
            tvResult?.setText(
                TwoColorBallDataUtil.getPrizeTipText(
                    myNumsLayout?.getRedHitCount() ?: 0,
                    myNumsLayout?.getBlueHitCount() ?: 0
                )
            )
            if (tvResult?.text == getString(R.string.result_tip_empty)) {
                tvResult?.setTextColor(ContextCompat.getColor(this, R.color.COLOR_000000))
            } else {
                tvResult?.setTextColor(ContextCompat.getColor(this, R.color.COLOR_04BE02))
            }
        }, 100)
    }

    @SuppressLint("SetTextI18n")
    private fun setupData() {
        tvHitCity?.text = "中奖城市：${TwoColorBallDataUtil.getPrizeCityStr()}"
        tvSequence?.text = "开奖期数：${TwoColorBallDataUtil.getPrizeSequenceStr()}"
        tvDate?.text = "开奖日期：${TwoColorBallDataUtil.getPrizeDateStr()}"
        prizeNumsLayout?.setBalls(finalBallArray, finalBallArray)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

}
