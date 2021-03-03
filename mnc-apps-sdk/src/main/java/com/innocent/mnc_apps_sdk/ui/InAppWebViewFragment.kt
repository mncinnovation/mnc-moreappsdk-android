package com.innocent.mnc_apps_sdk.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.innocent.mnc_apps_sdk.R
import com.innocent.mnc_apps_sdk.constant.Constant
import kotlinx.android.synthetic.main.fragment_webview.*

@SuppressLint("SetJavaScriptEnabled")
class InAppWebViewFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_webview, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadingProgressBar.visibility = View.VISIBLE
        inAppWebView.settings.javaScriptEnabled = true
        inAppWebView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl(url)
                return true
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                if (isAdded && activity != null) {
                    loadingProgressBar.visibility = View.GONE
                }
            }
        }
        inAppWebView.loadUrl(getWebUrl())
    }

    private fun getWebUrl(): String? {
        return arguments?.getString(Constant.webUrl)
    }
}