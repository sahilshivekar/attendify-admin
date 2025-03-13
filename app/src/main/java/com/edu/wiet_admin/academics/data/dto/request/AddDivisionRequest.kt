package com.edu.wiet_admin.academics.data.dto.request

// Request DTO for adding a division
data class AddDivisionRequest(
    val divisionCode: String,
    val semesterId: String
)