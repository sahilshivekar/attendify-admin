package com.attendify_admin.home.feature_schedule.domain.repository


import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.data.remote.dto.response.RoomDto
import com.attendify_admin.home.feature_schedule.data.dto.request.AddRoomRequest
import com.attendify_admin.home.feature_schedule.data.dto.request.UpdateRoomRequest
import retrofit2.Response

interface RoomRepository {
    suspend fun addRoom(requestBody: AddRoomRequest): Response<AttendifyApiResponse<RoomDto?>>

    suspend fun getRooms(
        searchQuery: String?,
        sortBy: String,
        sortOrder: String,
        page: Int,
        limit: Int
    ): Response<AttendifyApiResponse<List<RoomDto>?>>

    suspend fun getRoomById(roomId: Int): Response<AttendifyApiResponse<RoomDto?>>

    suspend fun updateRoom(requestBody: UpdateRoomRequest): Response<AttendifyApiResponse<RoomDto?>>

    suspend fun removeRoom(roomId: Int): Response<AttendifyApiResponse<String?>>
}