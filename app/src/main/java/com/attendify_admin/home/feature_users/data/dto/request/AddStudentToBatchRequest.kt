package com.attendify_admin.home.feature_users.data.dto.request

data class AddStudentToBatchRequest(
    val studentId: Int,
    val batchId: Int
)