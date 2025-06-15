package com.attendify_admin.common.data.remote.dto.response

import com.attendify_admin.common.domain.model.AttendanceSummary

data class AttendanceSummaryDto(
    val courseId: Int,
    val attendanceSummary: List<AttendanceRecordDto>
)

fun AttendanceSummaryDto.toAttendanceSummary(): AttendanceSummary {
    return AttendanceSummary(
        courseId = courseId,
        attendanceSummary = attendanceSummary.map { it.toAttendanceRecord() }
    )
}
