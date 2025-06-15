package com.attendify_admin.common.domain.model

data class AttendanceRecord(
    val attendanceDate: String,
    val totalStudents: Int,
    val presentStudents: Int,
    val attendanceId: Int
)