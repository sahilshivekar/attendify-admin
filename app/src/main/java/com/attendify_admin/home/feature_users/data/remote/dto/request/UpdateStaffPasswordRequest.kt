package com.attendify_admin.home.feature_users.data.remote.dto.request

data class UpdateStaffPasswordRequest(
    val id: Int,
    val password: String,
    val confirmPassword: String
)