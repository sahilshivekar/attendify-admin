package com.attendify_admin.admin_mgt.domain.use_case

import com.attendify_admin.admin_auth.data.remote.responses.VerificationCodeData
import com.attendify_admin.admin_auth.domain.repository.AuthRepository
import com.attendify_admin.admin_mgt.domain.repository.AdminMgtRepository
import com.attendify_admin.common.domain.RemoteUtils
import com.attendify_admin.common.data.remote.Resource
import com.attendify_admin.common.data.remote.AttendifyApiResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SendVerificationCodeToEmailUseCase @Inject constructor(
    private val adminMgtRepository: AdminMgtRepository
) {
    operator fun invoke(): Flow<Resource<AttendifyApiResponse<VerificationCodeData?>>> {
        return RemoteUtils.responseFlow {
            adminMgtRepository.sendVerificationCodeToEmail()
        }
    }
}