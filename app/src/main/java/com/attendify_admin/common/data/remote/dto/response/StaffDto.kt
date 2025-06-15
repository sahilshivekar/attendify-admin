package com.attendify_admin.common.data.remote.dto.response

import com.attendify_admin.common.domain.model.Staff

data class StaffDto(
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

fun StaffDto.toStaff(): Staff {
    return Staff(
        id = id,
        email = email,
        firstName = firstName,
        gender = gender,
        highestQualification = highestQualification,
        isActive = isActive,
        lastName = lastName,
        middleName = middleName,
        password = password,
        phoneNumber = phoneNumber,
        refreshToken = refreshToken,
        role = role,
        staffImagePublicId = staffImagePublicId,
        staffImageUrl = staffImageUrl
    )
}
