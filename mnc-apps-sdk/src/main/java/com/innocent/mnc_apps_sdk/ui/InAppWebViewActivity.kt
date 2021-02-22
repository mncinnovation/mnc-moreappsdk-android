package com.innocent.mnc_apps_sdk.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.innocent.mnc_apps_sdk.R
import com.innocent.mnc_apps_sdk.constant.Constant
import kotlinx.android.synthetic.main.activity_webview.*
import kotlinx.android.synthetic.main.view_toolbar.*

class InAppWebViewActivity: AppCompatActivity() {
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)

        setLogo()
        setupToolbar()

        inAppWebView.settings.javaScriptEnabled = true
        inAppWebView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl(url)
                return true
            }
        }
        inAppWebView.loadUrl(getWebUrl())
    }

    private fun setLogo() {
        Glide.with(this)
            .asBitmap()
            .load(Constant.mncLogo)
            .into(logoImageView)
    }

    private fun setupToolbar(){
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun getWebUrl(): String? {
        return intent.getStringExtra(Constant.webUrl)
    }

    companion object {
        fun startActivity(context: Context, url: String) {
            val intent = Intent(context, InAppWebViewActivity::class.java)
            intent.putExtra(Constant.webUrl, url)
            context.startActivity(intent)
        }
    }
}