package com.edu.wiet_admin.common.data.remote.reponseDto

data class Timetable(
    val id: Int,
    val timetableVersion: Int,
    val divisionId: Int,
    val Division: Division?,
    val createdAt: String,
    val updatedAt: String
)