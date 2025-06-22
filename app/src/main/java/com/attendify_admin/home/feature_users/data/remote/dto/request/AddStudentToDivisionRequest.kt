package com.attendify_admin.home.feature_users.data.remote.dto.request

data class AddStudentToDivisionRequest(
    val studentId: Int,
    val divisionId: Int
)