package com.attendify_admin.home.feature_schedule.data.dto.request

data class UpdateRoomRequest(
    val roomId: Int,
    val roomNumber: String?,
    val sittingCapacity: Int?
)