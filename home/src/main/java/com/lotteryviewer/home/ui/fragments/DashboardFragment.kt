package com.lotteryviewer.home.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.lotteryviewer.base.ui.BaseFragment
import com.lotteryviewer.home.R
import com.lotteryviewer.home.ui.viewmodel.DashboardViewModel
import com.lotteryviewer.sunset.ui.activities.PneumoniaActivity
import com.lotteryviewer.sunset.ui.activities.SunsetActivity
import com.lotteryviewer.sunset.util.SunsetDataUtil
import com.lotteryviewer.twocolorball.ui.activities.TwoColorBallMenuActivity

class DashboardFragment : BaseFragment() {

    private lateinit var dashboardViewModel: DashboardViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        dashboardViewModel = ViewModelProvider(this).get(DashboardViewModel::class.java)

        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnTwoColorBall: Button = view.findViewById(R.id.btn_two_color_ball)
        val btnSunsetBeijing: Button = view.findViewById(R.id.btn_sunset_beijing)
        val btnSunsetWuhan: Button = view.findViewById(R.id.btn_sunset_wuhan)
        val btnSunsetHuanggang: Button = view.findViewById(R.id.btn_sunset_huanggang)
        val btnPneumonia: Button = view.findViewById(R.id.btn_pneumonia)


        dashboardViewModel.text.observe(viewLifecycleOwner, Observer {
            // textView.text = it
        })


        btnTwoColorBall.setOnClickListener {
            startActivity(Intent(context, TwoColorBallMenuActivity::class.java))
        }
        btnSunsetBeijing.setOnClickListener {
            val intent = Intent(context, SunsetActivity::class.java)
            intent.putExtra(SunsetDataUtil.PARAM_URL_TYPE, SunsetDataUtil.URL_BEIJING)
            startActivity(intent)
        }
        btnSunsetWuhan.setOnClickListener {
            val intent = Intent(context, SunsetActivity::class.java)
            intent.putExtra(SunsetDataUtil.PARAM_URL_TYPE, SunsetDataUtil.URL_WUHAN)
            startActivity(intent)
        }
        btnSunsetHuanggang.setOnClickListener {
            val intent = Intent(context, SunsetActivity::class.java)
            intent.putExtra(SunsetDataUtil.PARAM_URL_TYPE, SunsetDataUtil.URL_HUANGGANG)
            startActivity(intent)
        }
        btnPneumonia.setOnClickListener {
            val intent = Intent(context, PneumoniaActivity::class.java)
            startActivity(intent)
        }

    }
}
