package com.attendify_admin.common.data.remote.dto.response

import com.attendify_admin.common.domain.model.AggregatedAttendance

data class AggregatedAttendanceDto(
    val courseId: Int,
    val courseName: String,
    val totalLectures: Int,
    val attendedLectures: Int
)

fun AggregatedAttendanceDto.toAggregatedAttendance(): AggregatedAttendance {
    return AggregatedAttendance(
        courseId = courseId,
        courseName = courseName,
        totalLectures = totalLectures,
        attendedLectures = attendedLectures
    )
}

