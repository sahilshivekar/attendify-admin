package com.attendify_admin.home.users.data.dto.request

data class AddStudentToSemesterRequest(
    val studentId: Int,
    val semesterId: Int
)