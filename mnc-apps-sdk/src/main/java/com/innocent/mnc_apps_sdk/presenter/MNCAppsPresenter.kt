package com.innocent.mnc_apps_sdk.presenter

import android.content.Context
import android.content.res.Configuration
import android.util.Log
import android.widget.Toast
import com.innocent.mnc_apps_sdk.constant.Constant
import com.innocent.mnc_apps_sdk.helper.PrefHelper
import com.innocent.mnc_apps_sdk.model.AppsModel
import com.innocent.mnc_apps_sdk.model.LayoutModel
import com.innocent.mnc_apps_sdk.model.ResponseData
import com.innocent.mnc_apps_sdk.service.Interactor
import com.innocent.mnc_apps_sdk.service.ServiceBuilder
import com.innocent.mnc_apps_sdk.ui.MNCAppsContract
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

class MNCAppsPresenter(val context: Context): MNCAppsContract.Presenter {
    private val TAG = "MNCAppsPresenter"
    private var view: MNCAppsContract.View? = null
    var interactor: Interactor = Interactor(ServiceBuilder().provideApiServiceWithCache(context))
    var compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun start() {
        getListApps()
    }

    override fun bind(view: MNCAppsContract.View) {
        this.view = view
    }

    override fun unbind() {
        this.view = null
    }

    override var listApps: MutableList<AppsModel> = ArrayList()

    override var layoutApps: LayoutModel = LayoutModel()

    override fun getListApps() {
        view?.showProgressBar(true)

        view?.userID?.let { it ->
            interactor.getDataNetwork(userID = it, packageName = view?.packageNameApps!!, platformType = view?.platformType!!)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<ResponseData>() {
                    override fun onNext(responseData: ResponseData) {
                        layoutApps = responseData.layout!!
                        listApps.clear()
                        listApps.addAll(responseData.items!!)

                        if (view?.screenSize!! >= 600) {
                            view?.showGridListApps()
                        } else {
                            view?.showListApps()
                        }
                        view?.showProgressBar(false)
                    }

                    override fun onError(e: Throwable) {
                        Log.d(TAG, "error: $e")
                        Toast.makeText(context, "[MNCAPPS] Error getting data", Toast.LENGTH_SHORT).show()
                        view?.showProgressBar(false)
                    }

                    override fun onComplete() {}

                })?.let { compositeDisposable.add(it) }
        }
    }
}