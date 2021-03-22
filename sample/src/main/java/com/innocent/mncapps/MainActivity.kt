package com.innocent.mncapps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.innocent.mnc_apps_sdk.constant.Constant
import com.innocent.mnc_apps_sdk.ui.MNCAppsActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        clickButton.setOnClickListener {
            val intent = Intent(this, MNCAppsActivity::class.java)
//            intent.putExtra(Constant.userID, "irKmyJJGGlNjMt7Kpg3xYlRT6dn1")
//            intent.putExtra(Constant.packageName, "an.android.app")
//            intent.putExtra(Constant.platformType, "android")
            startActivity(intent)
        }

        customClickButton.setOnClickListener {
            val intent = Intent(this, TestActivity::class.java)
            intent.putExtra(Constant.userID, "irKmyJJGGlNjMt7Kpg3xYlRT6dn1")
            intent.putExtra(Constant.packageName, "an.android.app")
            intent.putExtra(Constant.platformType, "android")
            startActivity(intent)
        }
    }
}