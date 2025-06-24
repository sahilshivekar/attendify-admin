package com.attendify_admin.home.feature_schedule.domain.use_case

import androidx.paging.PagingData
import androidx.paging.map
import com.attendify_admin.common.data.remote.dto.response.toRoom
import com.attendify_admin.common.domain.model.Room
import com.attendify_admin.home.feature_schedule.domain.repository.RoomRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetRoomsUseCase @Inject constructor(
    private val roomRepository: RoomRepository,
) {
    operator fun invoke(
        searchQuery: String?,
        sortBy: String,
        sortOrder: String
    ): Flow<PagingData<Room>> {
        return roomRepository.getRooms(
            searchQuery,
            sortBy,
            sortOrder
        ).map { pagingData ->
            pagingData.map { it.toRoom() }
        }
    }
}
