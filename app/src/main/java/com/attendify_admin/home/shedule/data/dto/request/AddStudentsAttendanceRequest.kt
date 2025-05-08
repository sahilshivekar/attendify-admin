package com.attendify_admin.home.shedule.data.dto.request

// Request DTO for adding students' attendance
data class AddStudentsAttendanceRequest(
    val attendanceId: String,
    val presentStudentIds: List<String>,
    val absentStudentIds: List<String>
)