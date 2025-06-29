package com.attendify_admin.common.domain.model

data class BranchCourseSemesters(
    val id: Int,
    val semesterNumber: Int,
    val branchId: Int,
    val branch: Branch?,
    val courseId: Int,
    val course: Course?
)