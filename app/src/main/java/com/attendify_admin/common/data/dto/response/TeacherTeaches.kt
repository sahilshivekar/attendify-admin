package com.attendify_admin.common.data.dto.response

data class TeacherTeaches(
    val id: Int,
    val teacherId: Int,
    val Staff: Staff?,
    val courseId: Int,
    val Course: Course?,
    val createdAt: String,
    val updatedAt: String,
)