package com.attendify_admin.common.data.remote.response_dto

data class StudentBatch(
    val id: Int,
    val endDate: String?,
    val startDate: String,
    val batchId: Int,
    val Batch: Batch,
    val studentId: Int,
    val Student: Student?,
    val createdAt: String,
    val updatedAt: String
)