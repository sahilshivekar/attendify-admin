package com.attendify_admin.home.users.data.dto.request

data class AddStudentToBatchRequest(
    val studentId: Int,
    val batchId: Int
)