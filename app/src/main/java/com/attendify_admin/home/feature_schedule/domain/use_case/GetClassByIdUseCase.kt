package com.attendify_admin.home.feature_schedule.domain.use_case

import com.attendify_admin.common.data.remote.Resource
import com.attendify_admin.common.data.remote.dto.response.toClass
import com.attendify_admin.common.domain.RemoteUtils
import com.attendify_admin.common.domain.model.Class
import com.attendify_admin.home.feature_schedule.domain.repository.ClassRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetClassByIdUseCase @Inject constructor(
    private val classRepository: ClassRepository
) {
    operator fun invoke(classId: Int): Flow<Resource<Class?>> = flow {

        emit(Resource.Loading<Class?>())

        val response = runCatching {
            classRepository.getClassById(classId)
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



