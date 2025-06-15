package com.attendify_admin.common.data.remote.dto.response

import com.attendify_admin.common.domain.model.AttendanceStudentAggregatedAndDetailedAttendance

data class AttendanceStudentAggregatedAndDetailedAttendanceDto(
    // get Attendance Of Student For Specific Course In Semester
    val aggregatedAttendance: AggregatedAttendanceDto,
    val detailedAttendanceRecord: List<DetailedAttendanceRecordDto>
)

fun AttendanceStudentAggregatedAndDetailedAttendanceDto.toAttendanceStudentAggregatedAndDetailedAttendance(): AttendanceStudentAggregatedAndDetailedAttendance {
    return AttendanceStudentAggregatedAndDetailedAttendance(
        aggregatedAttendance = aggregatedAttendance.toAggregatedAttendance(),
        detailedAttendanceRecord = detailedAttendanceRecord.map { it.toDetailedAttendanceRecord() }
    )
}
