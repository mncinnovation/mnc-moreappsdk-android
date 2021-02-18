package com.innocent.mnc_apps_sdk.utils

import android.graphics.Color
import java.io.StringWriter

class HexColor : Color() {
    fun fromHex(hexString: String): Int {
        val buffer = StringWriter()
        if (hexString.length == 6 || hexString.length == 7)
            buffer.write("ff")
        buffer.write(hexString.replaceFirst("#", ""))
        return parseColor(buffer.toString())
    }
}