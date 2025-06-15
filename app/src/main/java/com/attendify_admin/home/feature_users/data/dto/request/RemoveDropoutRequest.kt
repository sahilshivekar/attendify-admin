package com.attendify_admin.home.feature_users.data.dto.request

data class RemoveDropoutRequest(
    val studentId: Int, // Assuming studentId can be String, adjust if Int
    val academicStartYear: Int,
    val academicEndYear: Int
)
