package com.attendify_admin.home.feature_users.data.remote.dto.request

data class AddStudentToSemesterRequest(
    val studentId: Int,
    val semesterId: Int
)