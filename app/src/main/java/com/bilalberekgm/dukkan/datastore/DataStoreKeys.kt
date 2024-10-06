package com.bilalberekgm.dukkan.datastore

import androidx.datastore.preferences.core.stringPreferencesKey


object DataStoreKeys {
    val USER_FIRST_NAME_KEY = stringPreferencesKey("user_first_name")
    val USER_LAST_NAME_KEY = stringPreferencesKey("user_last_name")
    val JWT_TOKEN_KEY = stringPreferencesKey("jwt_token")
    val EMAIL_KEY = stringPreferencesKey("email")
    val PASSWORD = stringPreferencesKey("password")
    val USER_ROLE = stringPreferencesKey("role")
}