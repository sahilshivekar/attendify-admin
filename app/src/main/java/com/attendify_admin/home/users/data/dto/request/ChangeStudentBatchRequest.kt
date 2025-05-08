package com.attendify_admin.home.users.data.dto.request

data class ChangeStudentBatchRequest(
    val studentBatchId: Int,
    val batchId: Int,
    val newBatchStartDate: String
)