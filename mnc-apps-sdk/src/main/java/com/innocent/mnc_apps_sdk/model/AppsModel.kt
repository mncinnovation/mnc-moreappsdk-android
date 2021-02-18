package com.innocent.mnc_apps_sdk.model

class AppsModel {
    var appID: String? = null
    var appName: String? = null
    var webUrl: String? = null
    var youtube: String? = null
    var image: String? = null
    var open: String? = null
    var order: Int? = null
    var category: Translation? = null
    var description: Translation? = null
    var android: AppMeta? = null
    var ios: AppMeta? = null

    inner class AppMeta {
        var scheme: String? = null
        var store: String? = null
    }

    inner class Translation {
        var en: String? = null
        var id: String? = null
    }
}



