package com.attendify_admin.home.feature_schedule.domain.repository


import androidx.paging.PagingData
import com.attendify_admin.common.data.remote.dto.response.AttendifyApiResponse
import com.attendify_admin.common.data.remote.dto.response.RoomDto
import com.attendify_admin.home.feature_schedule.data.remote.dto.request.AddRoomRequest
import com.attendify_admin.home.feature_schedule.data.remote.dto.request.UpdateRoomRequest
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface RoomRepository {
    suspend fun addRoom(requestBody: AddRoomRequest): Response<AttendifyApiResponse<RoomDto?>>

    fun getRooms(
        searchQuery: String?,
        sortBy: String,
        sortOrder: String,
    ): Flow<PagingData<RoomDto>>

    suspend fun getRoomById(roomId: Int): Response<AttendifyApiResponse<RoomDto?>>

    suspend fun updateRoom(requestBody: UpdateRoomRequest): Response<AttendifyApiResponse<RoomDto?>>

    suspend fun removeRoom(roomId: Int): Response<AttendifyApiResponse<String?>>
}