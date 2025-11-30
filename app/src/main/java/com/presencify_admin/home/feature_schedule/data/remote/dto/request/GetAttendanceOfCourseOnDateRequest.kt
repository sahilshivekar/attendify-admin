package com.presencify_admin.home.feature_schedule.data.remote.dto.request

// Request DTO for getting attendance of a course on a specific date
data class GetAttendanceOfCourseOnDateRequest(
    val date: String,
    val courseId: Int,
    val divisionId: Int
)