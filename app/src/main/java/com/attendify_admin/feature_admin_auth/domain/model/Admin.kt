package com.attendify_admin.feature_admin_auth.domain.model

data class Admin(
    val id: Int,
    val username: String,
    val email: String,
    val password: String?,
    val isVerified: Boolean?,
)


