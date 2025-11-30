package com.presencify_admin.home.feature_schedule.data.remote.dto.request

// Request DTO for creating attendance
data class CreateAttendanceRequest(
    val classId: Int,
    val date: String
)