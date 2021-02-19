package com.innocent.mnc_apps_sdk.utils

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.View
import android.widget.Button

object LayoutUtils {
    fun getDrawable(view: View?, radius:Int?, color: String?) {
        val drawable = GradientDrawable()
        drawable.shape = GradientDrawable.RECTANGLE
        drawable.setStroke(1, Color.parseColor("#$color"))
        drawable.setColor(Color.parseColor("#$color"))
        drawable.cornerRadius = radius!!.toFloat()
        view?.setBackgroundDrawable(drawable)
    }
}