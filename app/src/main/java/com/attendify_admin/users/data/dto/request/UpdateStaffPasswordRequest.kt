package com.attendify_admin.users.data.dto.request

data class UpdateStaffPasswordRequest(
    val id: Int,
    val password: String,
    val confirmPassword: String
)