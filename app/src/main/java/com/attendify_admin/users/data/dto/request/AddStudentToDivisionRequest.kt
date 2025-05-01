package com.attendify_admin.users.data.dto.request

data class AddStudentToDivisionRequest(
    val studentId: Int,
    val divisionId: Int
)