package com.innocent.mncapps

import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.innocent.mnc_apps_sdk.constant.Constant
import com.innocent.mnc_apps_sdk.ui.MNCAppsActivity
import com.innocent.mnc_apps_sdk.ui.MNCAppsFragment

class TestActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tes)

        MNCAppsActivity.showFragment(R.id.appsFrameLayout, this)
    }
}