package com.attendify_admin.home.shedule.domain.repository


import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.data.dto.response.Room
import com.attendify_admin.home.shedule.data.dto.request.AddRoomRequest
import com.attendify_admin.home.shedule.data.dto.request.UpdateRoomRequest
import retrofit2.Response

interface RoomRepository {
    suspend fun addRoom(requestBody: AddRoomRequest): Response<AttendifyApiResponse<Room?>>

    suspend fun getRooms(
        searchQuery: String?,
        sortBy: String,
        sortOrder: String,
        page: Int,
        limit: Int
    ): Response<AttendifyApiResponse<List<Room?>>>

    suspend fun getRoomById(roomId: Int): Response<AttendifyApiResponse<Room?>>

    suspend fun updateRoom(requestBody: UpdateRoomRequest): Response<AttendifyApiResponse<Room?>>

    suspend fun removeRoom(roomId: Int): Response<AttendifyApiResponse<String?>>
}