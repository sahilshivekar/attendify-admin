package com.presencify_admin.home.feature_schedule.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.presencify_admin.common.data.remote.dto.response.PresencifyApiResponse
import com.presencify_admin.common.data.remote.dto.response.RoomDto
import com.presencify_admin.common.data.remote.dto.response.RoomListWithTotalCountDto
import com.presencify_admin.home.feature_schedule.data.remote.RoomApi
import com.presencify_admin.home.feature_schedule.data.remote.dto.request.AddRoomRequest
import com.presencify_admin.home.feature_schedule.data.remote.dto.request.UpdateRoomRequest
import com.presencify_admin.home.feature_schedule.domain.repository.RoomRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class RoomRepositoryImpl @Inject constructor(
    private val roomApi: RoomApi
) : RoomRepository {

    override suspend fun addRoom(requestBody: AddRoomRequest): Response<PresencifyApiResponse<RoomDto?>> =
        roomApi.addRoom(requestBody)

    override fun getRooms(
        searchQuery: String?,
        sortBy: String,
        sortOrder: String
    ): Flow<PagingData<RoomDto>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                GetRoomsPagingSource(
                    roomApi = roomApi,
                    searchQuery = searchQuery,
                    sortBy = sortBy,
                    sortOrder = sortOrder
                )
            }
        ).flow
    }
    override suspend fun getAllRooms(
        searchQuery: String?,
        sortBy: String,
        sortOrder: String
    ): Response<PresencifyApiResponse<RoomListWithTotalCountDto>> {
        return roomApi.getRooms(
            searchQuery = searchQuery,
            sortBy = sortBy,
            sortOrder = sortOrder,
            page = 1,
            limit = 10,
            getAll = true
        )
    }



    override suspend fun getRoomById(roomId: Int): Response<PresencifyApiResponse<RoomDto?>> =
        roomApi.getRoomById(roomId)

    override suspend fun updateRoom(requestBody: UpdateRoomRequest): Response<PresencifyApiResponse<RoomDto?>> =
        roomApi.updateRoom(requestBody)

    override suspend fun removeRoom(roomId: Int): Response<PresencifyApiResponse<String?>> =
        roomApi.removeRoom(roomId)
}