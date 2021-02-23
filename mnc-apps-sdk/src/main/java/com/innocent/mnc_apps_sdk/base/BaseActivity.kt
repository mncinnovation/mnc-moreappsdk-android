package com.innocent.mnc_apps_sdk.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.bumptech.glide.Glide
import com.innocent.mnc_apps_sdk.constant.Constant
import kotlinx.android.synthetic.main.view_toolbar.*

abstract class BaseActivity: AppCompatActivity() {
    private var isUsingToolbar = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)
        if (isUsingToolbar) configToolbar(viewToolbar)
    }

    protected fun setView(layoutResID: Int) {
        if (isUsingToolbar) configToolbar(viewToolbar)
    }

    protected fun setLogo() {
        Glide.with(this)
            .asBitmap()
            .load(Constant.mncLogo)
            .into(logoImageView)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun configToolbar(toolbar: Toolbar?) {
        toolbar?.let {
            setSupportActionBar(it)
        }
    }

    protected fun setDisplayHomeAsUpEnabled(isDisplayed: Boolean) {
        supportActionBar?.setDisplayHomeAsUpEnabled(isDisplayed)
        supportActionBar?.setDisplayShowHomeEnabled(isDisplayed)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }
}