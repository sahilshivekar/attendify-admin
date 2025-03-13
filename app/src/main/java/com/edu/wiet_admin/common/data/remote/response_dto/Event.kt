package com.edu.wiet_admin.common.data.remote.response_dto

data class Event(
    val id: Int,
    val title: String,
    val description: Any,
    val imagePublicId: Any,
    val imageUrl: Any,
    val location: Any,
    val registrationLink: String,
    val startDatetime: String,
    val endDatetime: String,
    val uploadedBy: Int,
    val Staff: Staff?,
    val createdAt: String,
    val updatedAt: String,
)
