package com.attendify_admin.home.shedule.data.dto.request

data class UpdateRoomRequest(
    val roomId: Int,
    val roomNumber: String?,
    val sittingCapacity: Int?
)