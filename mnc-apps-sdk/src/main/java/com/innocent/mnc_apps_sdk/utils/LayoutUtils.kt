@file:Suppress("DEPRECATION")

package com.innocent.mnc_apps_sdk.utils

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.View

object LayoutUtils {
    fun getDrawable(view: View?, radius:Int?, color: String?) {
        val drawable = GradientDrawable()
        drawable.shape = GradientDrawable.RECTANGLE
        drawable.setStroke(1, Color.parseColor("#$color"))
        drawable.setColor(Color.parseColor("#$color"))
        drawable.cornerRadius = radius!!.toFloat()
        view?.setBackgroundDrawable(drawable)
    }

    fun getRoundSize(size: Int?): Int {
        return when (size) {
            0 -> {
                0
            }
            1 -> {
                8
            }
            2 -> {
                16
            }
            else -> {
                8
            }
        }
    }
}