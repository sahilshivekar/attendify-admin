package com.attendify_admin.home.feature_schedule.data

import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.data.remote.dto.response.RoomDto
import com.attendify_admin.home.feature_schedule.data.dto.request.AddRoomRequest
import com.attendify_admin.home.feature_schedule.data.dto.request.UpdateRoomRequest
import com.attendify_admin.home.feature_schedule.domain.repository.RoomRepository
import retrofit2.Response
import javax.inject.Inject

class RoomRepositoryImpl @Inject constructor(
    private val roomApi: RoomApi
) : RoomRepository {

    override suspend fun addRoom(requestBody: AddRoomRequest): Response<AttendifyApiResponse<RoomDto?>> =
        roomApi.addRoom(requestBody)

    override suspend fun getRooms(
        searchQuery: String?,
        sortBy: String,
        sortOrder: String,
        page: Int,
        limit: Int
    ): Response<AttendifyApiResponse<List<RoomDto>?>> =
        roomApi.getRooms(searchQuery, sortBy, sortOrder, page, limit)

    override suspend fun getRoomById(roomId: Int): Response<AttendifyApiResponse<RoomDto?>> =
        roomApi.getRoomById(roomId)

    override suspend fun updateRoom(requestBody: UpdateRoomRequest): Response<AttendifyApiResponse<RoomDto?>> =
        roomApi.updateRoom(requestBody)

    override suspend fun removeRoom(roomId: Int): Response<AttendifyApiResponse<String?>> =
        roomApi.removeRoom(roomId)
}