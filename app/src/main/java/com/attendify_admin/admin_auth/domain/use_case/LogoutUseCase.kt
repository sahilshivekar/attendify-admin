package com.attendify_admin.admin_auth.domain.use_case

import com.attendify_admin.admin_auth.domain.repository.AuthRepository
import com.attendify_admin.common.domain.RemoteUtils
import com.attendify_admin.common.data.remote.Resource
import com.attendify_admin.common.data.remote.AttendifyApiResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    operator fun invoke(): Flow<Resource<AttendifyApiResponse<String?>>>  {
        return RemoteUtils.responseFlow {
            authRepository.logout()
        }
    }
}