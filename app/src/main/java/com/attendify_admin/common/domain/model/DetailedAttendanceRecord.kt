package com.attendify_admin.common.domain.model

data class DetailedAttendanceRecord(
    val attendanceId: Int,
    val date: String,
    val attendanceStatus: Boolean
)