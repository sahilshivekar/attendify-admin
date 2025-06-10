package com.attendify_admin.home.shedule.data.dto.request

data class MarkAttendanceRequest (
    val bleSessionUUID: String,
    val studentId: Int
)