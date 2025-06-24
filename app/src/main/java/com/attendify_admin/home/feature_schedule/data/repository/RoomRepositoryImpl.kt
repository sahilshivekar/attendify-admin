package com.attendify_admin.home.feature_schedule.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.attendify_admin.common.data.remote.dto.response.AttendifyApiResponse
import com.attendify_admin.common.data.remote.dto.response.RoomDto
import com.attendify_admin.common.data.remote.dto.response.RoomListWithTotalCountDto
import com.attendify_admin.home.feature_schedule.data.remote.RoomApi
import com.attendify_admin.home.feature_schedule.data.remote.dto.request.AddRoomRequest
import com.attendify_admin.home.feature_schedule.data.remote.dto.request.UpdateRoomRequest
import com.attendify_admin.home.feature_schedule.domain.repository.RoomRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class RoomRepositoryImpl @Inject constructor(
    private val roomApi: RoomApi
) : RoomRepository {

    override suspend fun addRoom(requestBody: AddRoomRequest): Response<AttendifyApiResponse<RoomDto?>> =
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
    ): Response<AttendifyApiResponse<RoomListWithTotalCountDto>> {
        return roomApi.getRooms(
            searchQuery = searchQuery,
            sortBy = sortBy,
            sortOrder = sortOrder,
            page = 1,
            limit = 10,
            getAll = true
        )
    }



    override suspend fun getRoomById(roomId: Int): Response<AttendifyApiResponse<RoomDto?>> =
        roomApi.getRoomById(roomId)

    override suspend fun updateRoom(requestBody: UpdateRoomRequest): Response<AttendifyApiResponse<RoomDto?>> =
        roomApi.updateRoom(requestBody)

    override suspend fun removeRoom(roomId: Int): Response<AttendifyApiResponse<String?>> =
        roomApi.removeRoom(roomId)
}