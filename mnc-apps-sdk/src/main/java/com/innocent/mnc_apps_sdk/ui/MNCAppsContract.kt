package com.innocent.mnc_apps_sdk.ui

import com.innocent.mnc_apps_sdk.base.BasePresenter
import com.innocent.mnc_apps_sdk.base.BaseView
import com.innocent.mnc_apps_sdk.model.AppsModel
import com.innocent.mnc_apps_sdk.model.LayoutModel

interface MNCAppsContract {
    interface View: BaseView<Presenter> {
        fun getUserID(): String?
        fun getPackageNameApps(): String?
        fun getPlatformType(): String?
        fun showListApps()
    }

    interface Presenter: BasePresenter<View> {
        var listApps: MutableList<AppsModel>
        var layoutApps: LayoutModel
        fun getListApps()
    }
}