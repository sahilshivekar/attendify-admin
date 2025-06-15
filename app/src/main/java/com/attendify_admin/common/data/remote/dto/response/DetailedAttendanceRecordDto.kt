package com.attendify_admin.common.data.remote.dto.response

import com.attendify_admin.common.domain.model.DetailedAttendanceRecord

data class DetailedAttendanceRecordDto(
    val attendanceId: Int,
    val date: String,
    val attendanceStatus: Boolean
)

fun DetailedAttendanceRecordDto.toDetailedAttendanceRecord(): DetailedAttendanceRecord {
    return DetailedAttendanceRecord(
        attendanceId = attendanceId,
        date = date,
        attendanceStatus = attendanceStatus
    )
}
