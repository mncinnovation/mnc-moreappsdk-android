package com.innocent.mnc_apps_sdk.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.innocent.mnc_apps_sdk.R
import com.innocent.mnc_apps_sdk.constant.Constant
import kotlinx.android.synthetic.main.view_toolbar.*

class MNCAppsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mnc_apps)

        setLogo()
    }

    private fun setLogo() {
        Glide.with(this)
            .asBitmap()
            .load(Constant.MNC_LOGO)
            .into(logoImageView)
    }

    fun mainStartActivity(context: Context) {
        val intent = Intent(context, MNCAppsActivity::class.java)
        context.startActivity(intent)
    }
}