package com.attendify_admin.admin_mgt.domain.use_case

import com.attendify_admin.admin_auth.data.remote.responses.AdminData
import com.attendify_admin.admin_mgt.data.remote.UpdateAdminDetailsRequestBody
import com.attendify_admin.admin_mgt.domain.repository.AdminMgtRepository
import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.data.remote.Resource
import com.attendify_admin.common.domain.RemoteUtils
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpdateAdminDetailsUseCase @Inject constructor(
    private val adminMgtRepository: AdminMgtRepository
) {
    operator fun invoke(requestBody: UpdateAdminDetailsRequestBody): Flow<Resource<AttendifyApiResponse<AdminData?>>> {
        return RemoteUtils.responseFlow {
            adminMgtRepository.updateAdminDetails(requestBody)
        }
    }
}
