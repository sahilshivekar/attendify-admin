package com.attendify_admin.common.data.remote.dto.response

import com.attendify_admin.common.domain.model.AttendanceRecord

data class AttendanceRecordDto(
    val attendanceDate: String,
    val totalStudents: Int,
    val presentStudents: Int,
    val attendanceId: Int
)

fun AttendanceRecordDto.toAttendanceRecord(): AttendanceRecord {
    return AttendanceRecord(
        attendanceDate = attendanceDate,
        totalStudents = totalStudents,
        presentStudents = presentStudents,
        attendanceId = attendanceId
    )
}
