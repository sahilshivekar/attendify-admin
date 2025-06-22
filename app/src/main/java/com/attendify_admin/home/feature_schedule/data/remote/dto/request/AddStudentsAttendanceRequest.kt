package com.attendify_admin.home.feature_schedule.data.remote.dto.request

// Request DTO for adding students' attendance
data class AddStudentsAttendanceRequest(
    val attendanceId: Int,
    val presentStudentIds: List<String>,
    val absentStudentIds: List<String>
)