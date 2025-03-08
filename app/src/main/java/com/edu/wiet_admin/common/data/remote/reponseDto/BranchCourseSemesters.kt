package com.edu.wiet_admin.common.data.remote.reponseDto

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