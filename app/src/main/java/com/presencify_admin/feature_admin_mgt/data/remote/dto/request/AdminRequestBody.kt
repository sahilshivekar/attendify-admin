package com.presencify_admin.feature_admin_mgt.data.remote.dto.request

data class AdminRequestBody(
    val email: String,
    val username: String,
    val password: String,
)