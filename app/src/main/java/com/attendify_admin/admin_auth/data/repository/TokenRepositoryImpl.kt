package com.attendify_admin.admin_auth.data.repository

import android.content.Context
import androidx.datastore.preferences.core.edit
import com.attendify_admin.admin_auth.domain.repository.TokenRepository
import com.attendify_admin.common.data.local.PreferencesKeys
import com.attendify_admin.common.data.local.dataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TokenRepositoryImpl (
    @ApplicationContext val context: Context
) : TokenRepository{

    override suspend fun saveAdminRefreshToken(adminRefreshToken: String) {
        context.dataStore.edit {
            it[PreferencesKeys.ADMIN_REFRESH_TOKEN] = "Bearer $adminRefreshToken"
        }
    }

    override suspend fun saveAdminAccessToken(adminAccessToken: String) {
        context.dataStore.edit {
            it[PreferencesKeys.ADMIN_ACCESS_TOKEN] = "Bearer $adminAccessToken"
        }
    }

    override fun readAdminAccessToken(): Flow<String?> {
        return context.dataStore.data.map {
            it[PreferencesKeys.ADMIN_ACCESS_TOKEN]
        }
    }

    override fun readAdminRefreshToken(): Flow<String?> {
        return context.dataStore.data.map {
            it[PreferencesKeys.ADMIN_REFRESH_TOKEN]
        }
    }

    override suspend fun clearAdminTokens() {
        context.dataStore.edit {
            it.remove(PreferencesKeys.ADMIN_ACCESS_TOKEN)
            it.remove(PreferencesKeys.ADMIN_REFRESH_TOKEN)
        }
    }

}