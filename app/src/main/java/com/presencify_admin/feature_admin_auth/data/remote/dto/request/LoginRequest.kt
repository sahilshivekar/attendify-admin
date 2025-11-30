package com.presencify_admin.feature_admin_auth.data.remote.dto.request


data class LoginRequest(
    val emailOrUsername: String,
    val password: String
)

