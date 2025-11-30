package com.presencify_admin.home.feature_users.data.remote.dto.request

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