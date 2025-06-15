package com.attendify_admin.common.domain.model

data class StudentListWithTotal(
    val students: List<Student>,
    val totalStudents: Int
)