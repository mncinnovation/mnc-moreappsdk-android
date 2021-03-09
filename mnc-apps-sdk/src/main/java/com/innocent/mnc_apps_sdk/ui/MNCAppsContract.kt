package com.innocent.mnc_apps_sdk.ui

import com.innocent.mnc_apps_sdk.base.BasePresenter
import com.innocent.mnc_apps_sdk.base.BaseView
import com.innocent.mnc_apps_sdk.model.AppsModel
import com.innocent.mnc_apps_sdk.model.LayoutModel

interface MNCAppsContract {
    interface View: BaseView<Presenter> {
        val screenSize: Int?
        fun showListApps()
        fun showGridListApps()
        fun showProgressBar(isShown: Boolean)
        var userID: String?
        var packageNameApps: String?
        var platformType: String?
    }

    interface Presenter: BasePresenter<View> {
        var listApps: MutableList<AppsModel>
        var layoutApps: LayoutModel
        fun getListApps()
    }
}