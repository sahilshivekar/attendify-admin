package com.attendify_admin.home.academics.data.dto.request

// Request DTO for adding a division
data class AddDivisionRequest(
    val divisionCode: String,
    val semesterId: String
)