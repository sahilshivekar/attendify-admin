package com.presencify_admin.home.feature_schedule.data.remote.dto.request

// Request DTO for updating a student's attendance
data class UpdateStudentAttendanceRequest(
    val attendanceId: Int,
    val studentId: Int,
    val newAttendanceStatus: Boolean
)