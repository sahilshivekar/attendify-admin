package com.attendify_admin.home.academics.data.dto.request

// Request DTO for updating a division
data class UpdateDivisionRequest(
    val id: String, // Division ID to update
    val divisionCode: String // New division code
)