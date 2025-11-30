package com.presencify_admin.home.feature_academics.data.remote.dto.request

// Request DTO for updating a university
data class UpdateUniversityRequest(
    val id: String,
    val name: String?,
    val abbreviation: String?
)