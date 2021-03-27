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
import com.lotteryviewer.twocolorball.ui.TwoColorBallPageActivity

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
        dashboardViewModel.text.observe(viewLifecycleOwner, Observer {
            // textView.text = it
        })

        btnTwoColorBall.setOnClickListener {
            startActivity(Intent(context, TwoColorBallPageActivity::class.java))
        }
    }
}