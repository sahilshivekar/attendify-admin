package com.attendify_admin.feature_admin_auth.data.remote.dto.responses

import com.attendify_admin.feature_admin_auth.domain.model.Login

data class LoginDto(
    val accessToken: String,
    val admin: AdminDto,
    val refreshToken: String,
)

fun LoginDto.toLogin(): Login {
    return Login(
        accessToken = accessToken,
        refreshToken = refreshToken,
        admin = admin
    )
}