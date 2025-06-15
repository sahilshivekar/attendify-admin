package com.attendify_admin.home.feature_schedule.data.dto.request

// Request DTO for getting attendance of a course throughout the semester
data class GetAttendanceOfCourseThroughoutSemesterRequest(
    val courseId: Int,
    val divisionId: Int
)