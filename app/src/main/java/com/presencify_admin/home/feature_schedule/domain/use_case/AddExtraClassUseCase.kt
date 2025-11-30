package com.presencify_admin.home.feature_schedule.domain.use_case

import com.presencify_admin.common.data.remote.Resource
import com.presencify_admin.common.data.remote.dto.response.toClass
import com.presencify_admin.common.domain.RemoteUtils
import com.presencify_admin.common.domain.model.Class
import com.presencify_admin.home.feature_schedule.data.remote.dto.request.AddExtraClassRequest
import com.presencify_admin.home.feature_schedule.domain.repository.ClassRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class AddExtraClassUseCase @Inject constructor(
    private val classRepository: ClassRepository
) {
    operator fun invoke(requestBody: AddExtraClassRequest): Flow<Resource<Class?>> = flow {

        emit(Resource.Loading())

        val response = runCatching {
            classRepository.addExtraClass(requestBody)
        }

        response.onSuccess { response ->
            if (response.isSuccessful) {
                emit(Resource.Success(response.body()?.data?.toClass()))
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
