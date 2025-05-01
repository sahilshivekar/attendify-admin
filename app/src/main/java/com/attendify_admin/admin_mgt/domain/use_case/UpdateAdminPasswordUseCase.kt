package com.attendify_admin.admin_mgt.domain.use_case

import com.attendify_admin.admin_mgt.data.remote.UpdatePasswordRequestBody
import com.attendify_admin.admin_mgt.domain.repository.AdminMgtRepository
import com.attendify_admin.common.data.remote.Resource
import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.domain.RemoteUtils
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpdateAdminPasswordUseCase @Inject constructor(
    private val adminMgtRepository: AdminMgtRepository
) {
    operator fun invoke(
        password: String,
        confirmPassword: String
    ): Flow<Resource<AttendifyApiResponse<Unit?>>> {
        val updatePasswordRequest = UpdatePasswordRequestBody(password, confirmPassword)
        return RemoteUtils.responseFlow {
            adminMgtRepository.updateAdminPassword(updatePasswordRequest)
        }
    }
}