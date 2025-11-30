package com.presencify_admin.home.feature_academics.data.remote.dto.request

// Request DTO for updating a semester
data class UpdateSemesterRequest(
    val semesterId: Int,
    val startDate: String,
    val endDate: String
)