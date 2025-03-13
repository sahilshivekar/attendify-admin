package com.edu.wiet_admin.common.data.remote.response_dto

data class Room(
    val id: Int,
    val roomNumber: String,
    val sittingCapacity: Int,
    val createdAt: String,
    val updatedAt: String
)