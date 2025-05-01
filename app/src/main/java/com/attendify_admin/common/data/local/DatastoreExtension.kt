package com.attendify_admin.common.data.local

import android.content.Context
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.attendify_admin.common.Constants

val Context.dataStore by preferencesDataStore(name = Constants.ADMIN_DATA)

object PreferencesKeys {
    val ADMIN_ACCESS_TOKEN = stringPreferencesKey("admin_access_token")
    val ADMIN_REFRESH_TOKEN = stringPreferencesKey("admin_refresh_token")
}