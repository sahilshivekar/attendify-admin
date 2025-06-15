package com.attendify_admin.home.feature_users.data.dto.request

data class UpdateStaffDetailsRequest(
    val id: Int,
    val firstName: String?,
    val middleName: String?,
    val lastName: String?,
    val email: String?,
    val role: String?,
    val gender: String?,
    val highestQualification: String?,
    val phoneNumber: String?,
    val isActive: Boolean?
)