package com.attendify_admin.home.academics.data.dto.request

// Request DTO for updating a semester
data class UpdateSemesterRequest(
    val semesterId: String,
    val startDate: String,
    val endDate: String
)