package com.attendify_admin.common.domain.model

data class Course(
    val id: Int,
    val code: String,
    val name: String,
    val optionalSubject: Any,
    val branchCourseSemesters: BranchCourseSemesters?,
    val schemeId: Int,
    val scheme: Scheme?
)