package com.attendify_admin.home.shedule.data.dto.request

data class SendAttendanceReportRequest (
    val startDate: String,
    val endDate: String,
    val studentIds: List<String>,
    val courseIds: List<String>,
    val semesterId: Int,
)