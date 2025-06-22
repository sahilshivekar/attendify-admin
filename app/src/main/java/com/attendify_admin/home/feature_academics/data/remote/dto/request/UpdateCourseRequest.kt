package com.attendify_admin.home.feature_academics.data.remote.dto.request

// Request DTO for updating a course
data class UpdateCourseRequest(
    val id: String, // Course ID to update
    val code: String?,
    val name: String?,
    val abbreviation: String?,
    val schemeId: Int?
)