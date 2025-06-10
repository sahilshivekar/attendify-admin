package com.attendify_admin.home.users.data.dto.request

data class RemoveDropoutRequest(
    val studentId: Int, // Assuming studentId can be String, adjust if Int
    val academicStartYear: Int,
    val academicEndYear: Int
)
