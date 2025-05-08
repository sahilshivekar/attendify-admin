package com.attendify_admin.home.academics.data.dto.request

// Request DTO for updating a scheme
data class UpdateSchemeRequest(
    val id: String,
    val name: String?,
    val abbreviation: String?
)