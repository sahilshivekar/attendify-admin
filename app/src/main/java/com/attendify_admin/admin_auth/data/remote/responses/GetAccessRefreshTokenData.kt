package com.attendify_admin.admin_auth.data.remote.responses

data class GetAccessRefreshTokenData(
    val accessToken: String,
    val refreshToken: String
)
