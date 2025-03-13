package com.edu.wiet_admin.common.data.remote.response_dto

data class Attendance(
    val id: Int,
    val date: String,
    val classId: Int,
    val Class: Class?,
    val createdAt: String,
    val updatedAt: String,
)