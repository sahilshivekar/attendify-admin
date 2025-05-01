package com.attendify_admin.shedule.data.dto.request

// Request DTO for getting attendance
data class GetAttendanceRequest(
    val date: String?,
    val attendanceId: String?,
    val classId: String?,
    val studentId: String?,
    val courseId: String?,
    val semesterId: String?,
    val divisionId: String?
)