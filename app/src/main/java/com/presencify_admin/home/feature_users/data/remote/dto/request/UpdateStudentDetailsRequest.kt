package com.presencify_admin.home.feature_users.data.remote.dto.request

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
    val branchId: Int?,
    val admissionYear: String,
    val admissionType: String,
    val prn: String,
    val parentEmail: String,
)