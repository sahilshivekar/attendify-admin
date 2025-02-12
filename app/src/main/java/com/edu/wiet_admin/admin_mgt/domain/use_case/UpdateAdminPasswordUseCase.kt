package com.edu.wiet_admin.admin_mgt.domain.use_case

import com.edu.wiet_admin.admin_mgt.data.remote.UpdatePasswordRequestBody
import com.edu.wiet_admin.admin_mgt.domain.repository.AdminMgtRepository
import com.edu.wiet_admin.common.data.remote.Resource
import com.edu.wiet_admin.common.data.remote.WietApiResponse
import com.edu.wiet_admin.common.domain.RemoteUtils
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpdateAdminPasswordUseCase @Inject constructor(
    private val adminMgtRepository: AdminMgtRepository
) {
    operator fun invoke(
        password: String,
        confirmPassword: String
    ): Flow<Resource<WietApiResponse<Unit?>>> {
        val updatePasswordRequest = UpdatePasswordRequestBody(password, confirmPassword)
        return RemoteUtils.responseFlow {
            adminMgtRepository.updateAdminPassword(updatePasswordRequest)
        }
    }
}