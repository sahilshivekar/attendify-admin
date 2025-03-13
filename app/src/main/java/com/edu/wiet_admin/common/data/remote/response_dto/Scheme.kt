package com.edu.wiet_admin.common.data.remote.response_dto

data class Scheme(
    val id: Int,
    val name: String,
    val universityId: Int,
    val University : University?,
    val createdAt: String,
    val updatedAt: String,
)