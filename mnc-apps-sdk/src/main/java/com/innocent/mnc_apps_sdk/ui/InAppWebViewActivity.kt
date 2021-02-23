package com.innocent.mnc_apps_sdk.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import com.innocent.mnc_apps_sdk.R
import com.innocent.mnc_apps_sdk.base.BaseActivity
import com.innocent.mnc_apps_sdk.constant.Constant
import kotlinx.android.synthetic.main.activity_webview.*

class InAppWebViewActivity: BaseActivity() {
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)

        setLogo()
        setDisplayHomeAsUpEnabled(true)

        showProgressBar(true)
        inAppWebView.settings.javaScriptEnabled = true
        inAppWebView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl(url)
                return true
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                showProgressBar(false)
            }
        }
        inAppWebView.loadUrl(getWebUrl())
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun getWebUrl(): String? {
        return intent.getStringExtra(Constant.webUrl)
    }

    private fun showProgressBar(isShown: Boolean) {
        if (isShown) {
            loadingProgressBar.visibility = View.VISIBLE
        } else {
            loadingProgressBar.visibility = View.GONE
        }
    }

    companion object {
        fun startActivity(context: Context, url: String) {
            val intent = Intent(context, InAppWebViewActivity::class.java)
            intent.putExtra(Constant.webUrl, url)
            context.startActivity(intent)
        }
    }
}