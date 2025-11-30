package com.presencify_admin.home.feature_schedule.domain.repository


import androidx.paging.PagingData
import com.presencify_admin.common.data.remote.dto.response.PresencifyApiResponse
import com.presencify_admin.common.data.remote.dto.response.RoomDto
import com.presencify_admin.common.data.remote.dto.response.RoomListWithTotalCountDto
import com.presencify_admin.home.feature_schedule.data.remote.dto.request.AddRoomRequest
import com.presencify_admin.home.feature_schedule.data.remote.dto.request.UpdateRoomRequest
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface RoomRepository {
    suspend fun addRoom(requestBody: AddRoomRequest): Response<PresencifyApiResponse<RoomDto?>>

    fun getRooms(
        searchQuery: String?,
        sortBy: String,
        sortOrder: String,
    ): Flow<PagingData<RoomDto>>

    suspend fun getAllRooms(
        searchQuery: String?,
        sortBy: String,
        sortOrder: String
    ): Response<PresencifyApiResponse<RoomListWithTotalCountDto>>


    suspend fun getRoomById(roomId: Int): Response<PresencifyApiResponse<RoomDto?>>

    suspend fun updateRoom(requestBody: UpdateRoomRequest): Response<PresencifyApiResponse<RoomDto?>>

    suspend fun removeRoom(roomId: Int): Response<PresencifyApiResponse<String?>>
}