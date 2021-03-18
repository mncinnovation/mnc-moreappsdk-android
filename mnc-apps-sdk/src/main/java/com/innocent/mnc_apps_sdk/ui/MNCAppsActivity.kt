package com.innocent.mnc_apps_sdk.ui

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.innocent.mnc_apps_sdk.R
import com.innocent.mnc_apps_sdk.base.BaseActivity
import com.innocent.mnc_apps_sdk.constant.Constant

class MNCAppsActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mnc_apps_screen)

        setScreenColor()
        setDisplayHomeAsUpEnabled(true)

        showFragment(R.id.appsFrameLayout, this)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    companion object {
        fun showFragment(view: Int, activity: FragmentActivity) {
            val mncAppsFragment = MNCAppsFragment()
            val bundle = Bundle()
            val fragmentManager = activity.supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()

            val userID: String? = activity.intent.getStringExtra(Constant.userID)
            val packageNameApps: String? = activity.intent.getStringExtra(Constant.packageName)
            val platformType: String? = activity.intent.getStringExtra(Constant.platformType)

            bundle.putString(Constant.userID, userID)
            bundle.putString(Constant.packageName, packageNameApps)
            bundle.putString(Constant.platformType, platformType)

            mncAppsFragment.arguments = bundle

            fragmentTransaction.replace(view, mncAppsFragment, "MNCAppsFragment")
            fragmentTransaction.commit()
        }
    }
}