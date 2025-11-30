package com.presencify_admin.home.feature_schedule.domain.use_case


import com.presencify_admin.common.data.remote.Resource
import com.presencify_admin.common.data.remote.dto.response.toRoom
import com.presencify_admin.common.domain.RemoteUtils
import com.presencify_admin.common.domain.model.Room
import com.presencify_admin.home.feature_schedule.domain.repository.RoomRepository
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetAllRoomsUseCase @Inject constructor(
    private val roomRepository: RoomRepository,
) {
    operator fun invoke(
        searchQuery: String?,
        sortBy: String,
        sortOrder: String
    ): Flow<Resource<ImmutableList<Room>>> = flow {
        emit(Resource.Loading())

        val response = runCatching {
            roomRepository.getAllRooms(
                searchQuery = searchQuery,
                sortBy = sortBy,
                sortOrder = sortOrder
            )
        }

        response.onSuccess { res ->
            if (res.isSuccessful) {
                emit(Resource.Success(res.body()?.data?.rooms?.map { it.toRoom() }?.toImmutableList()))
            } else {
                val errorMessage = RemoteUtils.getErrorMessage(res)
                emit(Resource.Error(message = errorMessage))
            }
        }

        response.onFailure { exception ->
            when (exception) {
                is IOException -> emit(Resource.Error(message = RemoteUtils.NETWORK_IO_ERROR_MESSAGE))
                else -> emit(Resource.Error(message = RemoteUtils.UNKNOWN_NETWORK_ERROR_MESSAGE))
            }
        }
    }
}
