package com.attendify_admin.users.data.dto.request

data class AddStudentToBatchRequest(
    val studentId: Int,
    val batchId: Int
)