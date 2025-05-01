package com.attendify_admin.admin_auth.data.remote.responses

data class LoginData(
    val accessToken: String,
    val admin: AdminData,
    val refreshToken: String
)