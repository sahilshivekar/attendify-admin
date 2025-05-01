package com.attendify_admin.admin_auth.domain.use_case

import com.attendify_admin.admin_auth.data.remote.GetAccessTokenRequest
import com.attendify_admin.admin_auth.data.remote.responses.GetAccessRefreshTokenData
import com.attendify_admin.admin_auth.domain.repository.AuthRepository
import com.attendify_admin.common.domain.RemoteUtils
import com.attendify_admin.common.data.remote.Resource
import com.attendify_admin.common.data.remote.AttendifyApiResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import javax.inject.Inject

class GetAccessRefreshTokenUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    operator fun invoke(refreshToken: String): Flow<Resource<AttendifyApiResponse<GetAccessRefreshTokenData?>>> {
        val getAccessTokenRequest = GetAccessTokenRequest(refreshToken)
        return RemoteUtils.responseFlow {
            authRepository.getAccessRefreshToken(getAccessTokenRequest)
        }
    }
}