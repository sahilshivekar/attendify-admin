package com.attendify_admin.home.feature_schedule.domain.use_case

import com.attendify_admin.common.data.remote.Resource
import com.attendify_admin.common.data.remote.dto.response.toCancelledClass
import com.attendify_admin.common.domain.RemoteUtils
import com.attendify_admin.common.domain.model.CancelledClass
import com.attendify_admin.home.feature_schedule.domain.repository.ClassRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetCancelledClassesUseCase @Inject constructor(
    private val classRepository: ClassRepository
) {
    operator fun invoke(
        divisionId: Int,
        batchId: Int,
        date: String,
        page: Int,
        limit: Int
    ): Flow<Resource<CancelledClass?>> = flow {

        emit(Resource.Loading<CancelledClass?>())

        val response = runCatching {
            classRepository.getCancelledClasses(
                divisionId,
                batchId,
                date,
                page,
                limit
            )
        }

        response.onSuccess { response ->
            if (response.isSuccessful) {
                emit(Resource.Success(response.body()?.data?.toCancelledClass()))
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
