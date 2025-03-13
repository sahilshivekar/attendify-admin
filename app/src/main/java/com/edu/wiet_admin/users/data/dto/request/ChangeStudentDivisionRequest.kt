package com.edu.wiet_admin.users.data.dto.request

data class ChangeStudentDivisionRequest(
    val studentDivisionId: Int,
    val divisionId: Int,
    val newDivisionStartDate: String
)