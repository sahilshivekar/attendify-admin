package com.presencify_admin.home.feature_schedule.data.remote.dto.request

data class SendAttendanceReportRequest (
    val startDate: String,
    val endDate: String,
    val studentIds: List<String>,
    val courseIds: List<String>,
    val semesterId: Int,
)