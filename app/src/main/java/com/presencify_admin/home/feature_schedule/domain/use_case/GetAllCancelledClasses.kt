package com.presencify_admin.home.feature_schedule.domain.use_case

import com.presencify_admin.common.data.remote.Resource
import com.presencify_admin.common.data.remote.dto.response.toCancelledClass
import com.presencify_admin.common.domain.RemoteUtils
import com.presencify_admin.common.domain.model.CancelledClass
import com.presencify_admin.home.feature_schedule.domain.repository.ClassRepository
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetAllCancelledClassesUseCase @Inject constructor(
    private val classRepository: ClassRepository,
) {
    operator fun invoke(
        divisionId: Int,
        batchId: Int,
        date: String
    ): Flow<Resource<ImmutableList<CancelledClass>>> = flow {
        emit(Resource.Loading())

        val response = runCatching {
            classRepository.getAllCancelledClasses(
                divisionId = divisionId,
                batchId = batchId,
                date = date
            )
        }

        response.onSuccess { res ->
            if (res.isSuccessful) {
                emit(Resource.Success(res.body()?.data?.cancelledClasses?.map { it.toCancelledClass() }?.toImmutableList()))
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

