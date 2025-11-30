package com.presencify_admin.common.data.remote.dto.response

import com.presencify_admin.common.domain.model.StudentDivision
import com.google.gson.annotations.SerializedName

data class StudentDivisionDto(
    val id: Int,
    val endDate: String?,
    val startDate: String,
    val studentId: Int,
    @SerializedName("Student")
    val student: StudentDto?,
    val divisionId: Int,
    @SerializedName("Division")
    val division: DivisionDto?,
    val createdAt: String,
    val updatedAt: String,
)

fun StudentDivisionDto.toStudentDivision(): StudentDivision {
    return StudentDivision(
        id = id,
        endDate = endDate,
        startDate = startDate,
        studentId = studentId,
        student = student?.toStudent(),
        divisionId = divisionId,
        division = division?.toDivision()
    )
}
