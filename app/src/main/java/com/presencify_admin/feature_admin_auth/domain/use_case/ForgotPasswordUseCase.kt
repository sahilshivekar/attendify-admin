package com.presencify_admin.feature_admin_auth.domain.use_case

import com.presencify_admin.common.data.remote.Resource
import com.presencify_admin.common.domain.RemoteUtils
import com.presencify_admin.common.domain.RemoteUtils.getErrorMessage
import com.presencify_admin.feature_admin_auth.data.remote.dto.request.ForgotPasswordRequest
import com.presencify_admin.feature_admin_auth.data.remote.dto.responses.toVerificationCode
import com.presencify_admin.feature_admin_auth.domain.model.VerificationCode
import com.presencify_admin.feature_admin_auth.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class ForgotPasswordUseCase @Inject constructor(
    private val authRepository: AuthRepository,
) {
    operator fun invoke(email: String): Flow<Resource<VerificationCode?>> = flow {

        val requestBody = ForgotPasswordRequest(email)

        emit(Resource.Loading<VerificationCode?>())

        val response = runCatching {
            authRepository.forgotPassword(requestBody)
        }

        response.onSuccess { response ->
            if (response.isSuccessful) {
                emit(Resource.Success(data = response.body()?.data?.toVerificationCode()))
            } else {
                val errorMessage = getErrorMessage(response)
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