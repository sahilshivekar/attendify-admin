package com.attendify_admin.common.domain.model

data class AttendanceStudentAggregatedAndDetailedAttendance(
    val aggregatedAttendance: AggregatedAttendance,
    val detailedAttendanceRecord: List<DetailedAttendanceRecord>
)