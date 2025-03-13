package com.edu.wiet_admin.common.data.remote.response_dto

data class Staff(
    val id: Int,
    val email: String,
    val firstName: String,
    val gender: String,
    val highestQualification: String,
    val isActive: Boolean,
    val lastName: String,
    val middleName: Any,
    val password: String,
    val phoneNumber: String,
    val refreshToken: Any,
    val role: String,
    val staffImagePublicId: Any,
    val staffImageUrl: Any,
    val createdAt: String,
    val updatedAt: String
)