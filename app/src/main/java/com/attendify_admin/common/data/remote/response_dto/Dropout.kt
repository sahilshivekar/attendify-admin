package com.attendify_admin.common.data.remote.response_dto


data class Dropout(
    val id: Int,
    val studentId: Int,
    val academicStartYear: Int,
    val academicEndYear: Int,
    val createdAt: String,
    val updatedAt: String
)