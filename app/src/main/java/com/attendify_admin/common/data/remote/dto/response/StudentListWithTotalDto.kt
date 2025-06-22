package com.attendify_admin.common.data.remote.dto.response

import com.attendify_admin.common.domain.model.StudentListWithTotal

data class StudentListWithTotalDto(
    val students: List<StudentDto>,
    val totalStudents: Int
)

