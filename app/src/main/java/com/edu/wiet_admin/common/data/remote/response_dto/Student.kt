package com.edu.wiet_admin.common.data.remote.response_dto

data class Student(
    val id: Int,
    val academicStatus: String,
    val admissionType: String,
    val admissionYear: Int,
    val dob: String,
    val email: String,
    val firstName: String,
    val gender: String,
    val lastName: String,
    val middleName: Any,
    val password: String,
    val phoneNumber: String,
    val prn: String,
    val refreshToken: Any,
    val studentImgPublicId: Any,
    val studentImgUrl: Any,
    val schemeId: Int,
    val Scheme: Scheme?,
    val StudentSemesters: List<StudentSemester>?,
    val createdAt: String,
    val updatedAt: String
)