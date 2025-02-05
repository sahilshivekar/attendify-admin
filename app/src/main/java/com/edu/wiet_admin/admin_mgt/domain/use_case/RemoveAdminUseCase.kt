package com.edu.wiet_admin.admin_mgt.domain.use_case

import com.edu.wiet_admin.common.data.remote.Resource
import com.edu.wiet_admin.common.data.remote.WietApiResponse
import com.edu.wiet_admin.admin_mgt.domain.repository.AdminMgtRepository
import com.edu.wiet_admin.common.domain.RemoteUtils
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RemoveAdminUseCase @Inject constructor(
    private val adminMgtRepository: AdminMgtRepository
) {
    operator fun invoke(): Flow<Resource<WietApiResponse<String?>>> {
        return RemoteUtils.responseFlow {
            adminMgtRepository.removeAdmin()
        }
    }
}
