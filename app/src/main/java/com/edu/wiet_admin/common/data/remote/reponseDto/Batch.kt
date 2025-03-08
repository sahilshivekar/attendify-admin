package com.edu.wiet_admin.common.data.remote.reponseDto

data class Batch(
    val id: Int,
    val batchCode: String,
    val divisionId: Int,
    val Division: Division?,
    val createdAt: String,
    val updatedAt: String,
)