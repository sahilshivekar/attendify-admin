package com.attendify_admin.shedule.data.dto.request

// Request DTO for getting attendance of a course throughout the semester
data class GetAttendanceOfCourseThroughoutSemesterRequest(
    val courseId: String,
    val divisionId: String
)