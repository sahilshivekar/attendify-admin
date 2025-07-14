package com.attendify_admin.common.data.remote.dto.response

import com.attendify_admin.common.domain.model.StudentSemester
import com.google.gson.annotations.SerializedName

data class StudentSemesterDto(
    val id: Int,
    val semesterId: Int,
    @SerializedName("Semester")
    val semester: SemesterDto?,
    val studentId: Int,
    @SerializedName("Student")
    val student: StudentDto?,
    val createdAt: String,
    val updatedAt: String
)


fun StudentSemesterDto.toStudentSemester(): StudentSemester {
    return StudentSemester(
        id = id,
        semesterId = semesterId,
        semester = semester?.toSemester(),
        studentId = studentId,
        student = student?.toStudent()
    )
}
