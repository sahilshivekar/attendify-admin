package com.attendify_admin.home.shedule.data.dto.request

// Request DTO for updating a student's attendance
data class UpdateStudentAttendanceRequest(
    val attendanceId: String,
    val studentId: String,
    val newAttendanceStatus: Boolean
)