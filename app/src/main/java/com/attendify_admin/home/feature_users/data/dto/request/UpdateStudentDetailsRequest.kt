package com.attendify_admin.home.feature_users.data.dto.request

data class UpdateStudentDetailsRequest(
    val id: Int,
    val firstName: String?,
    val middleName: String?,
    val lastName: String?,
    val email: String?,
    val gender: String?,
    val phoneNumber: String?,
    val dob: String?,
    val schemeId: Int?,
    val academicStatus: String?,
    val branchId: Int?
)