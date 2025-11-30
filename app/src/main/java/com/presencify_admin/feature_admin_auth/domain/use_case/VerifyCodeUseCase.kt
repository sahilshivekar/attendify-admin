package com.presencify_admin.feature_admin_auth.domain.use_case

import com.presencify_admin.common.data.remote.Resource
import com.presencify_admin.common.domain.RemoteUtils
import com.presencify_admin.feature_admin_auth.data.remote.dto.request.VerifyCodeRequest
import com.presencify_admin.feature_admin_auth.data.remote.dto.responses.toGetAccessRefreshToken
import com.presencify_admin.feature_admin_auth.domain.model.GetAccessRefreshToken
import com.presencify_admin.feature_admin_auth.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class VerifyCodeUseCase @Inject constructor(
    private val authRepository: AuthRepository,
) {
    operator fun invoke(
        email: String,
        code: String,
    ): Flow<Resource<GetAccessRefreshToken?>> = flow {

        emit(Resource.Loading<GetAccessRefreshToken?>())

        val requestBody = VerifyCodeRequest(code = code, email = email)

        val response = runCatching {
            authRepository.verifyCode(requestBody)
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