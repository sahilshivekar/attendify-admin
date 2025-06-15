package com.attendify_admin.feature_admin_auth.data.remote.dto.responses

import com.attendify_admin.feature_admin_auth.domain.model.GetAccessRefreshToken

data class GetAccessRefreshTokenDto(
    val accessToken: String,
    val refreshToken: String,
)

fun GetAccessRefreshTokenDto.toGetAccessRefreshToken(): GetAccessRefreshToken {
    return GetAccessRefreshToken(
        accessToken = accessToken,
        refreshToken = refreshToken
    )
}