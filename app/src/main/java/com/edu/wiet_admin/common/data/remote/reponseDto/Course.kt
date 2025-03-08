package com.edu.wiet_admin.common.data.remote.reponseDto

data class Course(
    val id: Int,
    val code: String,
    val name: String,
    val optionalSubject: Any,
    val BranchCourseSemesters: BranchCourseSemesters?,
    val schemeId: Int,
    val Scheme: Scheme?,
    val createdAt: String,
    val updatedAt: String,
)