package com.attendify_admin.home.academics.data.dto.request

// Request DTO for updating a course
data class UpdateCourseRequest(
    val id: String, // Course ID to update
    val code: String?,
    val name: String?,
    val abbreviation: String?,
    val schemeId: String?
)