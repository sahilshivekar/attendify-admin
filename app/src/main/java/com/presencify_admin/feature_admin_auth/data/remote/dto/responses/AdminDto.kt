package com.presencify_admin.feature_admin_auth.data.remote.dto.responses

import com.presencify_admin.feature_admin_auth.domain.model.Admin


data class AdminDto(
    val id: Int,
    val username: String,
    val email: String,
    val password: String?,
    val isVerified: Boolean?,
    val updatedAt: String?,
    val createdAt: String?,
)

fun AdminDto.toAdmin(): Admin {
    return Admin(
        id = id,
        username = username,
        email = email,
        password = password,
        isVerified = isVerified,
    )
}