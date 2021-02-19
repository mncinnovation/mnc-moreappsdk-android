package com.innocent.mnc_apps_sdk.presenter

import android.content.Context
import android.widget.Toast
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
    private var view: MNCAppsContract.View? = null
    private var interactor: Interactor? = null
    private val compositeDisposable: CompositeDisposable

    init {
        interactor = Interactor(ServiceBuilder().provideApiService())
        compositeDisposable = CompositeDisposable()
    }

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
        view?.getUserID()?.let {
            interactor?.getDataNetwork(userID = it, packageName = view?.getPackageNameApps()!!, platformType = view?.getPlatformType()!!)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribeWith(object : DisposableObserver<ResponseData>() {
                    override fun onNext(responseData: ResponseData) {
                        layoutApps = responseData.layout!!
                        listApps.clear()
                        listApps.addAll(responseData.items!!)
                        view?.showListApps()
                    }

                    override fun onError(e: Throwable) {
                        Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
                    }

                    override fun onComplete() {
                    }

                })?.let { compositeDisposable.add(it) }
        }
    }
}