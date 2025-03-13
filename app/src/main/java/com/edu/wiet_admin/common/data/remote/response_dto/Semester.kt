package com.edu.wiet_admin.common.data.remote.response_dto

data class Semester(
    val id: Int,
    val academicEndYear: Int,
    val academicStartYear: Int,
    val semesterNumber: Int,
    val endDate: String,
    val startDate: String,
    val branchId: Int,
    val Branch: Branch?,
    val schemeId: Int,
    val Scheme: Scheme?,
    val createdAt: String,
    val updatedAt: String,
)
