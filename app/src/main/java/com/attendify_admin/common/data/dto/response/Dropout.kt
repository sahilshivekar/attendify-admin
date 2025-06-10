package com.attendify_admin.common.data.dto.response


data class Dropout(
    val id: Int,
    val studentId: Int,
    val academicStartYear: Int,
    val academicEndYear: Int,
    val createdAt: String,
    val updatedAt: String
)