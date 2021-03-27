package com.lotteryviewer.base.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.FrameLayout
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
import com.lotteryviewer.base.R
import com.lotteryviewer.base.util.TextUtil
import com.lotteryviewer.base.util.WebViewSettingsUtil
import com.lotteryviewer.base.widget.MyWebChromeClient
import com.lotteryviewer.base.widget.MyWebViewClient

/**
 * A simple [Fragment] subclass.
 * Use the [BaseWebViewFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
open class BaseWebViewFragment : BaseFragment() {

    companion object {

        private const val ARG_PARAM_URL = "arg_param_url"

        fun newInstance(url: String) =
            BaseWebViewFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM_URL, url)
                }
            }
    }

    private var paramUrl: String? = null

    protected var baseFragmentRoot: FrameLayout? = null
    protected var baseWebView: WebView? = null
    protected var baseLoadingLayout: RelativeLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            paramUrl = it.getString(ARG_PARAM_URL)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_base_web_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        baseFragmentRoot = view.findViewById(R.id.fragment_web_view_root)
        baseWebView = view.findViewById(R.id.content_web_view)
        baseLoadingLayout = view.findViewById(R.id.loading_layout)

        baseWebView?.webChromeClient = MyWebChromeClient()
        baseWebView?.webViewClient = MyWebViewClient()

        // set WebView common properties
        WebViewSettingsUtil.setWebView(baseWebView)

        if (!TextUtil.isNullOrEmpty(paramUrl)) {
            baseWebView?.loadUrl(paramUrl!!)
        }
    }

    /**
     * 设置 loading 的背景颜色
     */
    protected fun setLoadingCoverColor(colorInt: Int) {
        baseLoadingLayout?.setBackgroundColor(colorInt)
    }

}
