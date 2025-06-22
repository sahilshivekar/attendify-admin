package com.attendify_admin.common.domain.model

data class Staff(
    val id: Int,
    val email: String,
    val firstName: String,
    val gender: String,
    val highestQualification: String?,
    val isActive: Boolean,
    val lastName: String,
    val middleName: String?,
    val password: String,
    val phoneNumber: String,
    val refreshToken: String?,
    val role: String,
    val staffImagePublicId: String?,
    val staffImageUrl: String?
)