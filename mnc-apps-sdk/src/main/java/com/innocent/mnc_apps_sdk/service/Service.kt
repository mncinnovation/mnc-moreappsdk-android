package com.innocent.mnc_apps_sdk.service

import com.innocent.mnc_apps_sdk.model.ResponseData
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Service {
    companion object {
        const val URL_PREFIX = "v0/b/mnc-apps-libs.appspot.com/o/"
    }

    @GET(URL_PREFIX + "json%2F{userID}%2F{packageName}-{platformType}?")
    fun getDataNetwork(
        @Path("userID") userID: String,
        @Path("packageName") packageName: String,
        @Path("platformType") platformType: String,
        @Query("alt") alt: String = "media"
    ): Observable<ResponseData>
}