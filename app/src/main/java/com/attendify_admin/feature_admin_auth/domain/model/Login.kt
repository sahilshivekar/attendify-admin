package com.attendify_admin.feature_admin_auth.domain.model

import com.attendify_admin.feature_admin_auth.data.remote.dto.responses.AdminDto

data class Login(
    val accessToken: String,
    val admin: AdminDto,
    val refreshToken: String
)