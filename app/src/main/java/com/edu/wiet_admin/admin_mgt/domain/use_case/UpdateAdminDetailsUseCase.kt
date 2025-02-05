package com.edu.wiet_admin.admin_mgt.domain.use_case

import com.edu.wiet_admin.admin_auth.data.remote.responses.AdminData
import com.edu.wiet_admin.admin_mgt.data.remote.UpdateAdminDetailsRequestBody
import com.edu.wiet_admin.common.data.remote.Resource
import com.edu.wiet_admin.common.data.remote.WietApiResponse
import com.edu.wiet_admin.admin_mgt.domain.repository.AdminMgtRepository
import com.edu.wiet_admin.common.domain.RemoteUtils
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpdateAdminDetailsUseCase @Inject constructor(
    private val adminMgtRepository: AdminMgtRepository
) {
    operator fun invoke(requestBody: UpdateAdminDetailsRequestBody): Flow<Resource<WietApiResponse<AdminData?>>> {
        return RemoteUtils.responseFlow {
            adminMgtRepository.updateAdminDetails(requestBody)
        }
    }
}
