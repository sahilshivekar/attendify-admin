package com.attendify_admin.shedule.data.dto.request

data class AddRoomRequest(
    val roomNumber: String?,
    val sittingCapacity: Int?
)