package com.innocent.mnc_apps_sdk.base

import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.innocent.mnc_apps_sdk.R
import com.innocent.mnc_apps_sdk.constant.Constant
import kotlinx.android.synthetic.main.activity_mnc_apps_screen.*
import kotlinx.android.synthetic.main.view_toolbar.*
import kotlinx.android.synthetic.main.view_toolbar.viewToolbar

abstract class BaseActivity : AppCompatActivity() {
    private var isUsingToolbar = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)
        if (isUsingToolbar) configToolbar(viewToolbar)
    }

    protected fun setScreenColor() {
        when (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
            Configuration.UI_MODE_NIGHT_NO -> {
                screenLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.white))
                viewToolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.white))
                logoImageView.setColorFilter(ContextCompat.getColor(this, R.color.black))
                Glide.with(this)
                    .asBitmap()
                    .load(Constant.mncLogo)
                    .into(logoImageView)
            }
            Configuration.UI_MODE_NIGHT_YES -> {
                screenLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.grey_900))
                viewToolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.grey_900))
                logoImageView.setColorFilter(ContextCompat.getColor(this, R.color.white))
                Glide.with(this)
                    .asBitmap()
                    .load(Constant.mncLogo)
                    .into(logoImageView)
            }
        }
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