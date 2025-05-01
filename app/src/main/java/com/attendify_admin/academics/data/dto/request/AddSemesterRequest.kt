package com.attendify_admin.academics.data.dto.request

// Request DTO for adding a semester
data class AddSemesterRequest(
    val branchId: String,
    val semesterNumber: Int,
    val academicStartYear: Int,
    val academicEndYear: Int,
    val startDate: String,
    val endDate: String,
    val schemeId: String,
    val optionalCourseIds: List<String>?
)