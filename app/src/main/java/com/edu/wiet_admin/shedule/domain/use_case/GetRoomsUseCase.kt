package com.edu.wiet_admin.shedule.domain.use_case

import com.edu.wiet_admin.common.data.remote.Resource
import com.edu.wiet_admin.common.data.remote.WietApiResponse
import com.edu.wiet_admin.common.data.remote.response_dto.Room
import com.edu.wiet_admin.common.domain.RemoteUtils
import com.edu.wiet_admin.shedule.domain.repository.RoomRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRoomsUseCase @Inject constructor(private val roomRepository: RoomRepository) {
    operator fun invoke(
        searchQuery: String?,
        sortBy: String,
        sortOrder: String,
        page: Int,
        limit: Int
    ): Flow<Resource<WietApiResponse<List<Room?>>>> {
        return RemoteUtils.responseFlow {
            roomRepository.getRooms(
                searchQuery,
                sortBy,
                sortOrder,
                page,
                limit
            )
        }
    }
}