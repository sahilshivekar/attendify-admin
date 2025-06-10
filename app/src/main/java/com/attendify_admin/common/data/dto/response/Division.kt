package com.attendify_admin.common.data.dto.response

data class Division(
    val id: Int,
    val divisionCode: String,
    val semesterId: Int,
    val Semester: Semester?,
    val Batch: Batch?,
    val createdAt: String,
    val updatedAt: String,
)