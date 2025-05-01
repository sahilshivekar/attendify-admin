package com.attendify_admin.common.data.remote.response_dto

data class Division(
    val id: Int,
    val divisionCode: String,
    val semesterId: Int,
    val Semester: Semester?,
    val Batch: Batch?,
    val createdAt: String,
    val updatedAt: String,
)