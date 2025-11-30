package com.presencify_admin.common.data.remote.dto.response

data class RoomListWithTotalCountDto(
    val rooms: List<RoomDto>,
    val totalCount: Int
)