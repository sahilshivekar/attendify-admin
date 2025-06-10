package com.attendify_admin.common.data.dto.response

data class Room(
    val id: Int,
    val roomNumber: String,
    val sittingCapacity: Int,
    val createdAt: String,
    val updatedAt: String
)