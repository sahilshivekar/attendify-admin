package com.attendify_admin.common.data.remote.response_dto

data class Student(
    val id: Int,
    val academicStatus: String,
    val admissionType: String,
    val admissionYear: Int,
    val dob: String?,
    val email: String,
    val firstName: String,
    val gender: String,
    val lastName: String,
    val middleName: String? = null,
    val password: String,
    val phoneNumber: String,
    val prn: String,
    val refreshToken: String?,
    val studentImgPublicId: String?,
    val studentImgUrl: String?,
    val schemeId: Int,
    val Scheme: Scheme?,
    val StudentSemesters: List<StudentSemester>?,
    val StudentDivisions: List<StudentDivision>?,
    val StudentBatches: List<StudentBatch>?,
    val createdAt: String,
    val updatedAt: String,
    val branchId: Int,
    val Branch: Branch?
)


