package com.edu.wiet_admin.admin_auth.domain.model

data class Admin(
    val id: Int,
    val email: String,
    val username: String,
    val isVerified: Boolean,
    val createdAt: String,
    val updatedAt: String
)