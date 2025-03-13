package com.edu.wiet_admin.common.data.remote.response_dto

data class Batch(
    val id: Int,
    val batchCode: String,
    val divisionId: Int,
    val Division: Division?,
    val createdAt: String,
    val updatedAt: String,
)