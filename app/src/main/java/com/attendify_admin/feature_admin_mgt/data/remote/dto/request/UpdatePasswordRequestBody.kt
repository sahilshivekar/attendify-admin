package com.attendify_admin.feature_admin_mgt.data.remote.dto.request

data class UpdatePasswordRequestBody(
    val password: String,
    val confirmPassword: String,
)


