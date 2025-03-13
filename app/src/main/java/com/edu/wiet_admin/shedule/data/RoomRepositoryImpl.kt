package com.edu.wiet_admin.shedule.data

import com.edu.wiet_admin.common.data.remote.WietApiResponse
import com.edu.wiet_admin.common.data.remote.response_dto.Room
import com.edu.wiet_admin.shedule.data.dto.request.AddRoomRequest
import com.edu.wiet_admin.shedule.data.dto.request.UpdateRoomRequest
import com.edu.wiet_admin.shedule.domain.repository.RoomRepository
import retrofit2.Response
import javax.inject.Inject

class RoomRepositoryImpl @Inject constructor(
    private val roomApi: RoomApi
) : RoomRepository {

    override suspend fun addRoom(requestBody: AddRoomRequest): Response<WietApiResponse<Room?>> =
        roomApi.addRoom(requestBody)

    override suspend fun getRooms(
        searchQuery: String?,
        sortBy: String,
        sortOrder: String,
        page: Int,
        limit: Int
    ): Response<WietApiResponse<List<Room?>>> =
        roomApi.getRooms(searchQuery, sortBy, sortOrder, page, limit)

    override suspend fun getRoomById(roomId: String): Response<WietApiResponse<Room?>> =
        roomApi.getRoomById(roomId)

    override suspend fun updateRoom(requestBody: UpdateRoomRequest): Response<WietApiResponse<Room?>> =
        roomApi.updateRoom(requestBody)

    override suspend fun removeRoom(roomId: String): Response<WietApiResponse<String?>> =
        roomApi.removeRoom(roomId)
}