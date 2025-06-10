package com.attendify_admin.common.data.dto.response

data class Scheme(
    val id: Int,
    val name: String,
    val universityId: Int,
    val University : University?,
    val createdAt: String,
    val updatedAt: String,
)