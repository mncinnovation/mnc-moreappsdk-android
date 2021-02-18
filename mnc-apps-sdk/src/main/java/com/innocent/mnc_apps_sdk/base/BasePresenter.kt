package com.innocent.mnc_apps_sdk.base

interface BasePresenter<V> {
    fun start()
    fun bind(view: V)
    fun unbind()
}