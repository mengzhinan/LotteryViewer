package com.lotteryviewer.home.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.lotteryviewer.base.ui.BaseFragment
import com.lotteryviewer.home.R
import com.lotteryviewer.home.ui.activities.AboutActivity
import com.lotteryviewer.home.ui.viewmodel.NotificationsViewModel

class NotificationsFragment : BaseFragment() {

    private lateinit var notificationsViewModel: NotificationsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        notificationsViewModel = ViewModelProvider(this).get(NotificationsViewModel::class.java)

        return inflater.inflate(R.layout.fragment_notifications, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val textView: TextView = view.findViewById(R.id.text_notifications_privacy)
        textView.setOnClickListener {
            startActivity(Intent(context, AboutActivity::class.java))
        }
        notificationsViewModel.text.observe(viewLifecycleOwner, Observer {
            // textView.text = it
        })
    }
}
