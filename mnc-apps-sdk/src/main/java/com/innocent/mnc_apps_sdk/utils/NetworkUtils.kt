@file:Suppress("DEPRECATION")

package com.innocent.mnc_apps_sdk.utils

import android.content.Context
import android.net.ConnectivityManager

object NetworkUtils {
    fun hasNetwork(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = cm.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }
}