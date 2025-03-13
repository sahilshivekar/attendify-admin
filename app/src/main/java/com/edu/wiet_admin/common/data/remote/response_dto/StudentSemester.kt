package com.edu.wiet_admin.common.data.remote.response_dto

data class StudentSemester(
    val id: Int,
    val semesterId: Int,
    val Semester: Semester,
    val studentId: Int,
    val Student: Student?,
    val createdAt: String,
    val updatedAt: String
)