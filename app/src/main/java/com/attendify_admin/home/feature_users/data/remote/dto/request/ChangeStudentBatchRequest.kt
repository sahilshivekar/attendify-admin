package com.attendify_admin.home.feature_users.data.remote.dto.request

data class ChangeStudentBatchRequest(
    val studentBatchId: Int,
    val batchId: Int,
    val newBatchStartDate: String
)