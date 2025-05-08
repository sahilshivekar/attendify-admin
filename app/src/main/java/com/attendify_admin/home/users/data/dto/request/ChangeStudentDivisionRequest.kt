package com.attendify_admin.home.users.data.dto.request

data class ChangeStudentDivisionRequest(
    val studentDivisionId: Int,
    val divisionId: Int,
    val newDivisionStartDate: String
)