package com.attendify_admin.common.data.remote.response_dto

data class BranchCourseSemesters(
    val id: Int,
    val semesterNumber: Int,
    val branchId: Int,
    val Branch: Branch,
    val courseId: Int,
    val Course: Course,
    val createdAt: String,
    val updatedAt: String,
)