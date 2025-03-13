package com.edu.wiet_admin.shedule.data.dto.request

// Request DTO for getting attendance of a course on a specific date
data class GetAttendanceOfCourseOnDateRequest(
    val date: String,
    val courseId: String,
    val divisionId: String
)