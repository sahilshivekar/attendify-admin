package com.attendify_admin.common.domain.model

data class TeacherTeaches(
    val id: Int,
    val teacherId: Int,
    val staff: Staff?,
    val courseId: Int,
    val course: Course?
)