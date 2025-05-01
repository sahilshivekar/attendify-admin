package com.attendify_admin.common.data.remote.response_dto

data class StudentListWithTotal(
    val students: List<Student>,
    val totalStudents: Int
)

