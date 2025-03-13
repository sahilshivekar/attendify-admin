package com.edu.wiet_admin.common.data.remote.response_dto

data class TeacherTeaches(
    val id: Int,
    val teacherId: Int,
    val Staff: Staff?,
    val courseId: Int,
    val Course: Course?,
    val createdAt: String,
    val updatedAt: String,
)