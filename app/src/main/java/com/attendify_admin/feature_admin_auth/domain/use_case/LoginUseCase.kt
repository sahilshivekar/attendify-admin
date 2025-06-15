package com.attendify_admin.feature_admin_auth.domain.use_case

import com.attendify_admin.common.data.remote.Resource
import com.attendify_admin.common.domain.RemoteUtils
import com.attendify_admin.feature_admin_auth.data.remote.dto.request.LoginRequest
import com.attendify_admin.feature_admin_auth.data.remote.dto.responses.toLogin
import com.attendify_admin.feature_admin_auth.domain.model.Login
import com.attendify_admin.feature_admin_auth.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject


class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    operator fun invoke(
        email: String,
        password: String
    ): Flow<Resource<Login?>> = flow {

        emit(Resource.Loading<Login?>())

        val requestBody = LoginRequest(email, password)

        val response = runCatching {
            authRepository.login(requestBody)
        }

        response.onSuccess { response ->
            if (response.isSuccessful) {
                emit(Resource.Success(response.body()?.data?.toLogin()))
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