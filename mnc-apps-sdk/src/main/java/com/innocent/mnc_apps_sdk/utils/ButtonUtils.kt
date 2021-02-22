package com.innocent.mnc_apps_sdk.utils

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import com.innocent.mnc_apps_sdk.model.AppsModel
import com.innocent.mnc_apps_sdk.ui.InAppWebViewActivity
import com.innocent.mnc_apps_sdk.utils.PackageNameUtils.getQueryMap


object ButtonUtils {
    fun getInstalledStatus(
        data: AppsModel?,
        packageManager: PackageManager?
    ): Boolean {
        val packageName = data?.android?.store?.let { getQueryMap(query = it)?.get("id") }
        return try {
            packageManager?.getPackageInfo(packageName, 0)
            true
        } catch (e: PackageManager.NameNotFoundException) {
            false
        }
    }

    fun getButtonLabel(data: AppsModel?, installed: Boolean): String {
        if (data?.open == "web") return "OPEN"
        return if (installed) {
            "OPEN"
        } else {
            "INSTALL"
        }
    }

    fun openFunction(context: Context, data: AppsModel?, installed: Boolean) {
        val packageName = data?.android?.store?.let { getQueryMap(query = it)?.get("id") }

        if (data?.open == "store") data.android?.store?.let {
            openBrowser(
                context = context,
                url = it
            )
        }
        if (data?.open == "web") data.webUrl?.let { InAppWebViewActivity.startActivity(context = context, url = it) }

        if (data?.open == "app") {
            if (installed) {
                openApps(context = context, packageName = packageName, packageManager = context.packageManager)
                return
            } else {
                data.android?.store?.let { openBrowser(context = context, url = it) }
            }
        }
    }

    private fun openBrowser(context: Context, url: String?) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        context.startActivity(browserIntent)
    }

    private fun openApps(context: Context, packageName: String?, packageManager: PackageManager?) {
        val intent: Intent? = packageName?.let {
            packageManager?.getLaunchIntentForPackage(
                it
            )
        }
        intent?.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent)
    }
}