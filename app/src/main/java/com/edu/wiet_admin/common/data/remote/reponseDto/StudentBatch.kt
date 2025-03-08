package com.edu.wiet_admin.common.data.remote.reponseDto

data class StudentBatch(
    val id: Int,
    val endDate: Any,
    val startDate: String,
    val batchId: Int,
    val Batch: Batch?,
    val studentId: Int,
    val Student: Student?,
    val createdAt: String,
    val updatedAt: String
)