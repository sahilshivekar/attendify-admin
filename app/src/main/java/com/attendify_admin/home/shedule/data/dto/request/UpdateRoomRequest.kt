package com.attendify_admin.home.shedule.data.dto.request

data class UpdateRoomRequest(
    val roomId: String,
    val roomNumber: String?,
    val sittingCapacity: Int?
)