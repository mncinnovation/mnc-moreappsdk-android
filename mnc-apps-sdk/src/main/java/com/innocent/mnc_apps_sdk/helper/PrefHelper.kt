package com.innocent.mnc_apps_sdk.helper

import android.content.Context
import android.content.SharedPreferences
import com.github.gfx.util.encrypt.EncryptedSharedPreferences
import com.github.gfx.util.encrypt.Encryption
import com.innocent.mnc_apps_sdk.model.ResponseData

class PrefHelper(val context: Context) {
    private fun getDefaultPreferences(): SharedPreferences {
        return EncryptedSharedPreferences(Encryption.getDefaultCipher(), context)
    }

    fun getPreferences(key: String?): String? {
        return getDefaultPreferences().getString(key, null)
    }

    fun savePreferences(key: String?, value: String?) {
        getDefaultPreferences().edit().putString(key, value).apply()
    }
}