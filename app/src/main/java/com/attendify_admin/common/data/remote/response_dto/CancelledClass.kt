package com.attendify_admin.common.data.remote.response_dto

data class CancelledClass(
    val id: Int,
    val classId: Int,
    val date: String,
    val reason: String?,
    val createdAt: String,
    val updatedAt: String
)