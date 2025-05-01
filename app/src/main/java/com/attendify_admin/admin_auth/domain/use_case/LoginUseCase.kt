package com.attendify_admin.admin_auth.domain.use_case

import com.attendify_admin.admin_auth.data.remote.LoginRequest
import com.attendify_admin.admin_auth.data.remote.responses.LoginData
import com.attendify_admin.admin_auth.domain.repository.AuthRepository
import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.data.remote.Resource
import com.attendify_admin.common.domain.RemoteUtils
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    operator fun invoke(
        email: String,
        password: String
    ): Flow<Resource<AttendifyApiResponse<LoginData?>>> {
        val loginRequest = LoginRequest(email, password)
        return RemoteUtils.responseFlow {
            authRepository.login(loginRequest)
        }
    }
}