package com.attendify_admin.feature_admin_mgt.domain.use_case

import com.attendify_admin.common.data.remote.Resource
import com.attendify_admin.common.domain.RemoteUtils
import com.attendify_admin.feature_admin_mgt.domain.repository.AdminMgtRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class RemoveAdminUseCase @Inject constructor(
    private val adminMgtRepository: AdminMgtRepository
) {
    operator fun invoke(): Flow<Resource<String?>> = flow {

        emit(Resource.Loading<String?>())

        val response = runCatching {
            adminMgtRepository.removeAdmin()
        }

        response.onSuccess { response ->
            if (response.isSuccessful) {
                emit(Resource.Success(response.body()?.data))
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
