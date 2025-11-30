package com.presencify_admin.common.domain.model

data class Semester(
    val id: Int,
    val academicEndYear: Int,
    val academicStartYear: Int,
    val semesterNumber: Int,
    val endDate: String,
    val startDate: String,
    val branchId: Int,
    val branch: Branch?,
    val schemeId: Int,
    val scheme: Scheme?
)