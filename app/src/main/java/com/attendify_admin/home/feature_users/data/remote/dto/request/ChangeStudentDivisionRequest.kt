package com.attendify_admin.home.feature_users.data.remote.dto.request

data class ChangeStudentDivisionRequest(
    val studentDivisionId: Int,
    val divisionId: Int,
    val newDivisionStartDate: String
)