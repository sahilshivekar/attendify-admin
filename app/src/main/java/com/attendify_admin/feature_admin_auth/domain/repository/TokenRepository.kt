package com.attendify_admin.feature_admin_auth.domain.repository

import kotlinx.coroutines.flow.Flow

interface TokenRepository {

    suspend fun saveAdminRefreshToken(adminRefreshToken: String)

    suspend fun saveAdminAccessToken(adminAccessToken: String)

    fun readAdminAccessToken(): Flow<String?>

    fun readAdminRefreshToken(): Flow<String?>

    suspend fun clearAdminTokens()
}