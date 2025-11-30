package com.presencify_admin.feature_admin_auth.data.remote.dto.request

data class VerifyCodeRequest(
    val code: String,
    val email: String?
)