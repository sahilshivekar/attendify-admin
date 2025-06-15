package com.attendify_admin.feature_admin_auth.domain.use_case

import com.attendify_admin.common.data.remote.Resource
import com.attendify_admin.common.domain.RemoteUtils
import com.attendify_admin.feature_admin_auth.data.remote.dto.request.GetAccessTokenRequest
import com.attendify_admin.feature_admin_auth.data.remote.dto.responses.toGetAccessRefreshToken
import com.attendify_admin.feature_admin_auth.domain.model.GetAccessRefreshToken
import com.attendify_admin.feature_admin_auth.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetAccessRefreshTokenUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    operator fun invoke(refreshToken: String): Flow<Resource<GetAccessRefreshToken?>> = flow {

        emit(Resource.Loading<GetAccessRefreshToken?>())

        val requestBody = GetAccessTokenRequest(refreshToken)

        val response = runCatching {
            authRepository.getAccessRefreshToken(requestBody)
        }

        response.onSuccess { response ->
            if (response.isSuccessful) {
                emit(Resource.Success(response.body()?.data?.toGetAccessRefreshToken()))
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
