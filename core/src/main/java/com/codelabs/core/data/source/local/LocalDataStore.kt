package com.codelabs.core.data.source.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalDataStore @Inject constructor(private val context: Context) {

    val accessToken: Flow<String?> =
        context.dataStore.data.map { pref ->
            pref[ACCESS_TOKEN]
        }

    val refreshToken: Flow<String?> =
        context.dataStore.data.map { pref ->
            pref[REFRESH_TOKEN]
        }

    suspend fun saveAccessToken(accessToken: String) {
        context.dataStore.edit { pref ->
            pref[ACCESS_TOKEN] = accessToken
        }
    }

    suspend fun saveRefreshToken(refreshToken: String) {
        context.dataStore.edit { pref ->
            pref[REFRESH_TOKEN] = refreshToken
        }
    }

    suspend fun deleteAccessToken() {
        context.dataStore.edit { pref ->
            pref.remove(ACCESS_TOKEN)
        }
    }

    suspend fun deleteRefreshToken() {
        context.dataStore.edit { pref ->
            pref.remove(REFRESH_TOKEN)
        }
    }

    suspend fun clearTokens() {
        context.dataStore.edit { pref ->
            pref.remove(ACCESS_TOKEN)
            pref.remove(REFRESH_TOKEN)
        }
    }

    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("agrimate_pref")
        val ACCESS_TOKEN = stringPreferencesKey("accessToken")
        val REFRESH_TOKEN = stringPreferencesKey("refreshToken")
    }
}