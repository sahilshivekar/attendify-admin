package com.attendify_admin.home.feature_schedule.data.dto.request

// Request DTO for getting attendance
data class GetAttendanceRequest(
    val date: String?,
    val attendanceId: Int?,
    val classId: Int?,
    val studentId: Int?,
    val courseId: Int?,
    val semesterId: Int?,
    val divisionId: Int?
)