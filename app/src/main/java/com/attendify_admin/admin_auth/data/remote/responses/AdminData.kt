package com.attendify_admin.admin_auth.data.remote.responses


data class AdminData(
    val id: Int,
    val username: String,
    val email: String,
    val password: String?,
    val isVerified: Boolean?,
    val updatedAt: String?,
    val createdAt: String?,
)
