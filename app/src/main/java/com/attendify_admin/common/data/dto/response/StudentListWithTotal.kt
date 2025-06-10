package com.attendify_admin.common.data.dto.response

data class StudentListWithTotal(
    val students: List<Student>,
    val totalStudents: Int
)

