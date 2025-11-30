package com.presencify_admin.common.domain.model

data class AttendanceSummary(
    val courseId: Int,
    val attendanceSummary: List<AttendanceRecord>
)