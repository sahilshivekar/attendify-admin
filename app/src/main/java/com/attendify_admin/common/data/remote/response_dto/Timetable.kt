package com.attendify_admin.common.data.remote.response_dto

data class Timetable(
    val id: Int,
    val timetableVersion: Int,
    val divisionId: Int,
    val Division: Division?,
    val createdAt: String,
    val updatedAt: String
)