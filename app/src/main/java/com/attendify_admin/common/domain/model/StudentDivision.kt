package com.attendify_admin.common.domain.model

data class StudentDivision(
    val id: Int,
    val endDate: String?,
    val startDate: String,
    val studentId: Int,
    val student: Student?,
    val divisionId: Int,
    val division: Division
)