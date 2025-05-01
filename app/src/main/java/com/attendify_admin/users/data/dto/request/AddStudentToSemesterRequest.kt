package com.attendify_admin.users.data.dto.request

data class AddStudentToSemesterRequest(
    val studentId: Int,
    val semesterId: Int
)