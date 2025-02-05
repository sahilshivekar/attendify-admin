package com.edu.wiet_admin.admin_auth.data.remote.responses

import com.edu.wiet_admin.admin_auth.domain.model.Admin

data class AdminDto(
    val id: Int,
    val username: String,
    val email: String,
    val isVerified: Boolean,
    val updatedAt: String,
    val createdAt: String,
)

fun AdminDto.toAdmin(): Admin {
    return Admin(
        id = id,
        username = username,
        email = email,
        isVerified = isVerified,
        updatedAt = updatedAt,
        createdAt = createdAt
    )
}