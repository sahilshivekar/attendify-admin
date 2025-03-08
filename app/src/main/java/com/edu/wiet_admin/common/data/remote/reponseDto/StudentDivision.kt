package com.edu.wiet_admin.common.data.remote.reponseDto

data class StudentDivision(
    val id: Int,
    val endDate: Any,
    val startDate: String,
    val studentId: Int,
    val Student: Student?,
    val divisionId: Int,
    val Division: Division?,
    val createdAt: String,
    val updatedAt: String,
)