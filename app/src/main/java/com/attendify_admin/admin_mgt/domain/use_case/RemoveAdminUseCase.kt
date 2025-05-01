package com.attendify_admin.admin_mgt.domain.use_case

import com.attendify_admin.common.data.remote.Resource
import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.admin_mgt.domain.repository.AdminMgtRepository
import com.attendify_admin.common.domain.RemoteUtils
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RemoveAdminUseCase @Inject constructor(
    private val adminMgtRepository: AdminMgtRepository
) {
    operator fun invoke(): Flow<Resource<AttendifyApiResponse<String?>>> {
        return RemoteUtils.responseFlow {
            adminMgtRepository.removeAdmin()
        }
    }
}
