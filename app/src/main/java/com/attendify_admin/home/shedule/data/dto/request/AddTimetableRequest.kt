package com.attendify_admin.home.shedule.data.dto.request

data class AddTimetableRequest(
    val divisionId: Int,
    val timetableVersion: Int = 1
)