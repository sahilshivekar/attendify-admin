package com.edu.wiet_admin.shedule.data.dto.request

data class AddTimetableRequest(
    val divisionId: Int,
    val timetableVersion: Int = 1
)