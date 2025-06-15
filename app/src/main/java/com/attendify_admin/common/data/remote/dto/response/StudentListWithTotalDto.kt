package com.attendify_admin.common.data.remote.dto.response

import com.attendify_admin.common.domain.model.StudentListWithTotal

data class StudentListWithTotalDto(
    val students: List<StudentDto>,
    val totalStudents: Int
)

fun StudentListWithTotalDto.toStudentListWithTotal(): StudentListWithTotal {
    return StudentListWithTotal(
        students = students.map { it.toStudent() },
        totalStudents = totalStudents
    )
}
