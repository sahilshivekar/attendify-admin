package com.presencify_admin.home.feature_academics.data.remote.dto.request

// Request DTO for adding a course
data class AddCourseRequest(
    val code: String,
    val name: String,
    val optionalSubject: String?,
    val schemeId: Int
)