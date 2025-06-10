package com.attendify_admin.common.data.dto.response

data class Timetable(
    val id: Int,
    val timetableVersion: Int,
    val divisionId: Int,
    val Division: Division?,
    val createdAt: String,
    val updatedAt: String
)