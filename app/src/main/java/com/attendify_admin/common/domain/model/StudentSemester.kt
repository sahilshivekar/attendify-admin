package com.attendify_admin.common.domain.model

data class StudentSemester(
    val id: Int,
    val semesterId: Int,
    val semester: Semester?,
    val studentId: Int,
    val student: Student?
)