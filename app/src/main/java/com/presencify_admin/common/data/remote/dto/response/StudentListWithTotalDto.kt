package com.presencify_admin.common.data.remote.dto.response

data class StudentListWithTotalDto(
    val students: List<StudentDto>,
    val totalStudents: Int
)

