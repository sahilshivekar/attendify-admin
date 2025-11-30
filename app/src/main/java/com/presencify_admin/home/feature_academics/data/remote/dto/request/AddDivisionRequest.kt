package com.presencify_admin.home.feature_academics.data.remote.dto.request

// Request DTO for adding a division
data class AddDivisionRequest(
    val divisionCode: String,
    val semesterId: Int
)