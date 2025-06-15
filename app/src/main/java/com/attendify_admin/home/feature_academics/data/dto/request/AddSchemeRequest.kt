package com.attendify_admin.home.feature_academics.data.dto.request

// Request DTO for adding a scheme
data class AddSchemeRequest(
    val name: String,
    val abbreviation: String,
    val universityId: Int
)

