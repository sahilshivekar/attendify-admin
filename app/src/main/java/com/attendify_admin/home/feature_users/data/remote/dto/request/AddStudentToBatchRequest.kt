package com.attendify_admin.home.feature_users.data.remote.dto.request

data class AddStudentToBatchRequest(
    val studentId: Int,
    val batchId: Int
)