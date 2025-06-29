package com.attendify_admin.common.domain.model

data class StudentBatch(
    val id: Int,
    val endDate: String?,
    val startDate: String,
    val batchId: Int,
    val batch: Batch?,
    val studentId: Int,
    val student: Student?
)