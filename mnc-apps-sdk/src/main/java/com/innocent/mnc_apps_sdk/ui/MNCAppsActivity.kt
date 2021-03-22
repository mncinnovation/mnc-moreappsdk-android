package com.innocent.mnc_apps_sdk.ui

import android.os.Bundle
import android.widget.Toast
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
            var platformType: String? = activity.intent.getStringExtra(Constant.platformType)

            if (userID == null || packageNameApps == null) {
                Toast.makeText(activity, "Please fill user ID or package name", Toast.LENGTH_SHORT).show()
            }

            if (platformType == null) {
                platformType = "android"
            }

            bundle.putString(Constant.userID, userID)
            bundle.putString(Constant.packageName, packageNameApps)
            bundle.putString(Constant.platformType, platformType)

            mncAppsFragment.arguments = bundle

            fragmentTransaction.replace(view, mncAppsFragment, "MNCAppsFragment")
            fragmentTransaction.commit()
        }
    }
}