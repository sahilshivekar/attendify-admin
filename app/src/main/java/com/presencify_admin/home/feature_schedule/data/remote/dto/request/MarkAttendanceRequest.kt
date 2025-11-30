package com.presencify_admin.home.feature_schedule.data.remote.dto.request

data class MarkAttendanceRequest (
    val bleSessionUUID: String,
    val studentId: Int
)