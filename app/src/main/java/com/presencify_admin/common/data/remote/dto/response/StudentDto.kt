package com.presencify_admin.common.data.remote.dto.response

import com.presencify_admin.common.domain.model.Student
import com.google.gson.annotations.SerializedName

data class StudentDto(
    val id: Int,
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
    val parentEmail: String?,
    val prn: String,
    val refreshToken: String?,
    val studentImgPublicId: String?,
    val studentImgUrl: String?,
    val schemeId: Int,
    @SerializedName("Scheme")
    val scheme: SchemeDto?,
    @SerializedName("StudentSemesters")
    val studentSemesters: List<StudentSemesterDto>?,
    @SerializedName("StudentDivisions")
    val studentDivisions: List<StudentDivisionDto>?,
    @SerializedName("StudentBatches")
    val studentBatches: List<StudentBatchDto>?,
    val createdAt: String,
    val updatedAt: String,
    val branchId: Int,
    @SerializedName("Branch")
    val branch: BranchDto?
)

fun StudentDto.toStudent(): Student {
    return Student(
        id = id,
        admissionType = admissionType,
        admissionYear = admissionYear,
        dob = dob,
        email = email,
        firstName = firstName,
        gender = gender,
        lastName = lastName,
        middleName = middleName,
        password = password,
        phoneNumber = phoneNumber,
        parentEmail = parentEmail,
        prn = prn,
        studentImgPublicId = studentImgPublicId,
        studentImgUrl = studentImgUrl,
        schemeId = schemeId,
        scheme = scheme?.toScheme(),
        studentSemesters = studentSemesters?.map { it.toStudentSemester() },
        studentDivisions = studentDivisions?.map { it.toStudentDivision() },
        studentBatches = studentBatches?.map { it.toStudentBatch() },
        branchId = branchId,
        branch = branch?.toBranch()
    )
}



