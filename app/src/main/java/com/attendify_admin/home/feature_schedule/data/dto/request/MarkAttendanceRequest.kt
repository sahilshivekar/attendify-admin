package com.attendify_admin.home.feature_schedule.data.dto.request

data class MarkAttendanceRequest (
    val bleSessionUUID: String,
    val studentId: Int
)