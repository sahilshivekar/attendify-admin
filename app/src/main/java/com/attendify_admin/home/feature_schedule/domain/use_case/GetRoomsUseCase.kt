package com.attendify_admin.home.feature_schedule.domain.use_case

import com.attendify_admin.common.data.remote.Resource
import com.attendify_admin.common.data.remote.dto.response.toRoom
import com.attendify_admin.common.domain.RemoteUtils
import com.attendify_admin.common.domain.model.Room
import com.attendify_admin.home.feature_schedule.domain.repository.RoomRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetRoomsUseCase @Inject constructor(
    private val roomRepository: RoomRepository,
) {
    operator fun invoke(
        searchQuery: String?,
        sortBy: String,
        sortOrder: String,
        page: Int,
        limit: Int,
    ): Flow<Resource<List<Room>?>> = flow {

        emit(Resource.Loading<List<Room>?>())

        val response = runCatching {
            roomRepository.getRooms(
                searchQuery,
                sortBy,
                sortOrder,
                page,
                limit
            )
        }

        response.onSuccess { response ->
            if (response.isSuccessful) {
                emit(Resource.Success(response.body()?.data?.map { it.toRoom() }))
            } else {
                val errorMessage = RemoteUtils.getErrorMessage(response)
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
