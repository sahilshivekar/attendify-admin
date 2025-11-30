package com.presencify_admin.home.feature_schedule.data.remote.dto.request

data class UpdateRoomRequest(
    val roomId: Int,
    val roomNumber: String?,
    val sittingCapacity: Int?
)