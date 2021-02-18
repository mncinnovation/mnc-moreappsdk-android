package com.innocent.mnc_apps_sdk.service

import com.innocent.mnc_apps_sdk.model.ResponseData
import io.reactivex.Observable

class Interactor(var service: Service) {

    fun getDataNetwork(userID: String, packageName: String, platformType: String): Observable<ResponseData> {
        return service.getDataNetwork(userID, packageName, platformType)
    }
}