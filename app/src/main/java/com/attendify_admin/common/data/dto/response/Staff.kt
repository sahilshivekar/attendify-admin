package com.attendify_admin.common.data.dto.response

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
    val refreshToken: Any,
    val role: String,
    val staffImagePublicId: Int?,
    val staffImageUrl: String?,
    val createdAt: String,
    val updatedAt: String
)