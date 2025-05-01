package com.attendify_admin.academics.data.dto.request

// Request DTO for adding a course to a branch with a semester number
data class AddCourseToBranchWithSemesterNumberRequest(
    val courseId: String,
    val branchId: String,
    val semesterNumber: Int
)