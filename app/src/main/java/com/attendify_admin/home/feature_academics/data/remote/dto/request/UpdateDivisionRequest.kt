package com.attendify_admin.home.feature_academics.data.remote.dto.request

// Request DTO for updating a division
data class UpdateDivisionRequest(
    val id: String, // Division ID to update
    val divisionCode: String // New division code
)