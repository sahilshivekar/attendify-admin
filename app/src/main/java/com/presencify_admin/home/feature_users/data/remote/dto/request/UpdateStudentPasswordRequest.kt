package com.presencify_admin.home.feature_users.data.remote.dto.request

data class UpdateStudentPasswordRequest(
    val id: Int,
    val password: String,
    val confirmPassword: String
)