package com.edu.wiet_admin.shedule.domain.repository


import com.edu.wiet_admin.common.data.remote.WietApiResponse
import com.edu.wiet_admin.common.data.remote.response_dto.Room
import com.edu.wiet_admin.shedule.data.dto.request.UpdateRoomRequest
import com.edu.wiet_admin.shedule.data.dto.request.AddRoomRequest
import retrofit2.Response

interface RoomRepository {
    suspend fun addRoom(requestBody: AddRoomRequest): Response<WietApiResponse<Room?>>

    suspend fun getRooms(
        searchQuery: String?,
        sortBy: String,
        sortOrder: String,
        page: Int,
        limit: Int
    ): Response<WietApiResponse<List<Room?>>>

    suspend fun getRoomById(roomId: String): Response<WietApiResponse<Room?>>

    suspend fun updateRoom(requestBody: UpdateRoomRequest): Response<WietApiResponse<Room?>>

    suspend fun removeRoom(roomId: String): Response<WietApiResponse<String?>>
}