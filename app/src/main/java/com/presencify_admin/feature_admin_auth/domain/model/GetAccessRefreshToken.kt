package com.presencify_admin.feature_admin_auth.domain.model

data class GetAccessRefreshToken(
    val accessToken: String,
    val refreshToken: String
)