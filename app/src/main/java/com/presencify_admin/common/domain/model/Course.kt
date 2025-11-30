package com.presencify_admin.common.domain.model

data class Course(
    val id: Int,
    val code: String,
    val name: String,
    val optionalSubject: String?,
    val branchCourseSemesters: List<BranchCourseSemesters>?,
    val schemeId: Int,
    val scheme: Scheme?
)