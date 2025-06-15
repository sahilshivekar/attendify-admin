package com.attendify_admin.common.domain.model

data class Student(
    val id: Int,
    val admissionType: String,
    val admissionYear: Int,
    val dob: String?,
    val email: String,
    val firstName: String,
    val gender: String,
    val lastName: String,
    val middleName: String?,
    val password: String,
    val phoneNumber: String,
    val parentEmail: String?,
    val prn: String,
    val studentImgPublicId: String?,
    val studentImgUrl: String?,
    val schemeId: Int,
    val scheme: Scheme?,
    val studentSemesters: List<StudentSemester>?,
    val studentDivisions: List<StudentDivision>?,
    val studentBatches: List<StudentBatch>?,
    val branchId: Int,
    val branch: Branch?
)