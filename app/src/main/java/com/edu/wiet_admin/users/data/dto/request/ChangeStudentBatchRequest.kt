package com.edu.wiet_admin.users.data.dto.request

data class ChangeStudentBatchRequest(
    val studentBatchId: Int,
    val batchId: Int,
    val newBatchStartDate: String
)