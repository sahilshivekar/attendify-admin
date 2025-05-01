package com.attendify_admin.academics.data.dto.request

// Request DTO for updating a university
data class UpdateUniversityRequest(
    val id: String,
    val name: String?,
    val abbreviation: String?
)