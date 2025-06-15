package com.attendify_admin.home.feature_academics.data.dto.request

// Request DTO for adding a semester
data class AddSemesterRequest(
    val branchId: Int,
    val semesterNumber: Int,
    val academicStartYear: Int,
    val academicEndYear: Int,
    val startDate: String,
    val endDate: String,
    val schemeId: Int,
    val optionalCourseIds: List<String>?
)