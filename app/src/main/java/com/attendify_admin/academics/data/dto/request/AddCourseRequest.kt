package com.attendify_admin.academics.data.dto.request

// Request DTO for adding a course
data class AddCourseRequest(
    val code: String,
    val name: String,
    val abbreviation: String,
    val schemeId: String
)