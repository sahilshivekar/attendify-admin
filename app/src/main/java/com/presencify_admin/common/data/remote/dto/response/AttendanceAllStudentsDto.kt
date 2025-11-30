package com.presencify_admin.common.data.remote.dto.response

import com.presencify_admin.common.domain.model.AttendanceAllStudents

data class AttendanceAllStudentsDto(
    val attendanceSummary: AttendanceSummaryDto,
    val attendanceRecord: List<AttendanceRecordDto>
)

fun AttendanceAllStudentsDto.toAttendanceAllStudents(): AttendanceAllStudents {
    return AttendanceAllStudents(
        attendanceSummary = attendanceSummary.toAttendanceSummary(),
        attendanceRecord = attendanceRecord.map { it.toAttendanceRecord() }
    )
}

