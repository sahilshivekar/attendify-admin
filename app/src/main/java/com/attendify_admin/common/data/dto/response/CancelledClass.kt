package com.attendify_admin.common.data.dto.response

data class CancelledClass(
    val id: Int,
    val classId: Int,
    val date: String,
    val reason: String?,
    val createdAt: String,
    val updatedAt: String
)