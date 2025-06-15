package com.attendify_admin.common.domain.model

data class AggregatedAttendance(
    val courseId: Int,
    val courseName: String,
    val totalLectures: Int,
    val attendedLectures: Int
)