package com.attendify_admin.home.shedule.data.dto.request

// Request DTO for creating attendance
data class CreateAttendanceRequest(
    val classId: Int,
    val date: String
)