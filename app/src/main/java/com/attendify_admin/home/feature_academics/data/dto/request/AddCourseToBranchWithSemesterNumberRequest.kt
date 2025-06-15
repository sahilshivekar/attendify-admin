package com.attendify_admin.home.feature_academics.data.dto.request

// Request DTO for adding a course to a branch with a semester number
data class AddCourseToBranchWithSemesterNumberRequest(
    val courseId: Int,
    val branchId: Int,
    val semesterNumber: Int
)