package com.innocent.mnc_apps_sdk.ui

import com.innocent.mnc_apps_sdk.base.BasePresenter
import com.innocent.mnc_apps_sdk.base.BaseView
import com.innocent.mnc_apps_sdk.model.AppsModel

interface MNCAppsContract {
    interface View: BaseView<Presenter> {
        fun showListApps()
    }

    interface Presenter: BasePresenter<View> {
        var listApps: MutableList<AppsModel>
        fun getListApps()
    }
}