package com.attendify_admin.home.feature_users.data.dto.request

data class UpdateStaffPasswordRequest(
    val id: Int,
    val password: String,
    val confirmPassword: String
)