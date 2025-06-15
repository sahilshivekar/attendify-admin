package com.attendify_admin.home.feature_academics.data.dto.request

// Request DTO for adding a division
data class AddDivisionRequest(
    val divisionCode: String,
    val semesterId: Int
)