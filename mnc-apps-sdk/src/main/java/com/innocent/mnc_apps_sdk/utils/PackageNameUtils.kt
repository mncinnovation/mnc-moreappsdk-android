package com.innocent.mnc_apps_sdk.utils

object PackageNameUtils {
    fun getQueryMap(query: String): Map<String, String>? {
        val map: MutableMap<String, String> = HashMap()
        val queryString = query.split("\\?".toRegex()).toTypedArray()
        if (queryString.size > 1) {
            val params = queryString[1].split("&".toRegex()).toTypedArray()
            for (param in params) {
                val name = param.split("=".toRegex()).toTypedArray()[0]
                val value = param.split("=".toRegex()).toTypedArray()[1]
                map[name] = value
            }
        }
        return map
    }
}