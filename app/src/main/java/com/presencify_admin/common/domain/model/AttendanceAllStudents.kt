package com.presencify_admin.common.domain.model

data class AttendanceAllStudents(
    val attendanceSummary: AttendanceSummary,
    val attendanceRecord: List<AttendanceRecord>
)