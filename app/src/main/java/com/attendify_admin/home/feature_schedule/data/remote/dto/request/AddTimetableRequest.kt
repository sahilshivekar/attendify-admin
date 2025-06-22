package com.attendify_admin.home.feature_schedule.data.remote.dto.request

data class AddTimetableRequest(
    val divisionId: Int,
    val timetableVersion: Int = 1
)