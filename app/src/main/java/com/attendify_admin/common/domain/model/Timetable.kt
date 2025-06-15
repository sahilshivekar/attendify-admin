package com.attendify_admin.common.domain.model

data class Timetable(
    val id: Int,
    val timetableVersion: Int,
    val divisionId: Int,
    val division: Division?
)