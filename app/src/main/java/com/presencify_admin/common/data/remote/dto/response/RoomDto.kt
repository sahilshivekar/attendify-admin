package com.presencify_admin.common.data.remote.dto.response

import com.presencify_admin.common.domain.model.Room

data class RoomDto(
    val id: Int,
    val roomNumber: String,
    val sittingCapacity: Int,
    val createdAt: String,
    val updatedAt: String
)

fun RoomDto.toRoom(): Room {
    return Room(
        id = id,
        roomNumber = roomNumber,
        sittingCapacity = sittingCapacity
    )
}
