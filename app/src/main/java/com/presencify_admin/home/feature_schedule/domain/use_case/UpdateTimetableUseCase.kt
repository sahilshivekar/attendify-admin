package com.presencify_admin.home.feature_schedule.domain.use_case

import com.presencify_admin.common.data.remote.Resource
import com.presencify_admin.common.data.remote.dto.response.toTimetable
import com.presencify_admin.common.domain.RemoteUtils
import com.presencify_admin.common.domain.model.Timetable
import com.presencify_admin.home.feature_schedule.data.remote.dto.request.UpdateTimetableRequest
import com.presencify_admin.home.feature_schedule.domain.repository.TimetableRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class UpdateTimetableUseCase @Inject constructor(
    private val timetableRepository: TimetableRepository
) {
    operator fun invoke(
        requestBody: UpdateTimetableRequest
    ): Flow<Resource<Timetable?>> = flow {

        emit(Resource.Loading<Timetable?>())

        val response = runCatching {
            timetableRepository.updateTimetable(requestBody)
        }

        response.onSuccess { response ->
            if (response.isSuccessful) {
                emit(Resource.Success(response.body()?.data?.toTimetable()))
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
