package com.lotteryviewer.home.webpage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.lotteryviewer.home.R

class WebPageFragment : Fragment() {

    private lateinit var webPageViewModel: WebPageViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        webPageViewModel =
            ViewModelProvider(this).get(WebPageViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_web_page, container, false)
        val textView: TextView = root.findViewById(R.id.text_web_page)
        webPageViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}