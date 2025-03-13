package com.edu.wiet_admin.academics.data.dto.request

// Request DTO for updating a scheme
data class UpdateSchemeRequest(
    val id: String,
    val name: String?,
    val abbreviation: String?
)