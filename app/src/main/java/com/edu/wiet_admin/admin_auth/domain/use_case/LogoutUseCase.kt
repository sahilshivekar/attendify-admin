package com.edu.wiet_admin.admin_auth.domain.use_case

import com.edu.wiet_admin.admin_auth.domain.repository.AuthRepository
import com.edu.wiet_admin.common.domain.RemoteUtils
import com.edu.wiet_admin.common.data.remote.Resource
import com.edu.wiet_admin.common.data.remote.WietApiResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    operator fun invoke(): Flow<Resource<WietApiResponse<String?>>>  {
        return RemoteUtils.responseFlow {
            authRepository.logout()
        }
    }
}