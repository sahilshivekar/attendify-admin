package com.attendify_admin.common.data.dto.response

data class Batch(
    val id: Int,
    val batchCode: String,
    val divisionId: Int,
    val Division: Division?,
    val createdAt: String,
    val updatedAt: String,
)