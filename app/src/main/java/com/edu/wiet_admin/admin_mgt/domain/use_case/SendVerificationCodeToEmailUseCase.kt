package com.edu.wiet_admin.admin_mgt.domain.use_case

import com.edu.wiet_admin.admin_auth.data.remote.responses.VerificationCodeData
import com.edu.wiet_admin.admin_auth.domain.repository.AuthRepository
import com.edu.wiet_admin.admin_mgt.domain.repository.AdminMgtRepository
import com.edu.wiet_admin.common.domain.RemoteUtils
import com.edu.wiet_admin.common.data.remote.Resource
import com.edu.wiet_admin.common.data.remote.WietApiResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SendVerificationCodeToEmailUseCase @Inject constructor(
    private val adminMgtRepository: AdminMgtRepository
) {
    operator fun invoke(): Flow<Resource<WietApiResponse<VerificationCodeData?>>> {
        return RemoteUtils.responseFlow {
            adminMgtRepository.sendVerificationCodeToEmail()
        }
    }
}