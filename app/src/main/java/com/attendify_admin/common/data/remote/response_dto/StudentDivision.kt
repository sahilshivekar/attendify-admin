package com.attendify_admin.common.data.remote.response_dto

data class StudentDivision(
    val id: Int,
    val endDate: String?,
    val startDate: String,
    val studentId: Int,
    val Student: Student?,
    val divisionId: Int,
    val Division: Division,
    val createdAt: String,
    val updatedAt: String,
)