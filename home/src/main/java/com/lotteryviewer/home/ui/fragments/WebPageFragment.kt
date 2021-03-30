package com.lotteryviewer.home.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.lotteryviewer.base.ui.BaseWebViewFragment
import com.lotteryviewer.home.ui.viewmodel.WebPageViewModel

class WebPageFragment : BaseWebViewFragment() {

    private lateinit var webPageViewModel: WebPageViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        webPageViewModel = ViewModelProvider(this).get(WebPageViewModel::class.java)

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        baseWebView?.loadUrl(WebPageViewModel.WEB_URL)
    }

}
