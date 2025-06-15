package com.attendify_admin.home.feature_users.data.dto.request

data class AddStudentToSemesterRequest(
    val studentId: Int,
    val semesterId: Int
)