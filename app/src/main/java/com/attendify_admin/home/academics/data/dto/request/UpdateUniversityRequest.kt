package com.attendify_admin.home.academics.data.dto.request

// Request DTO for updating a university
data class UpdateUniversityRequest(
    val id: String,
    val name: String?,
    val abbreviation: String?
)