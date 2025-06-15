package com.attendify_admin.home.feature_schedule.data.dto.request

// Request DTO for updating a student's attendance
data class UpdateStudentAttendanceRequest(
    val attendanceId: Int,
    val studentId: Int,
    val newAttendanceStatus: Boolean
)