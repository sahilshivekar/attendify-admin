package com.edu.wiet_admin.admin_auth.data.remote.responses

data class LoginData(
    val accessToken: String,
    val admin: AdminDto,
    val refreshToken: String
)