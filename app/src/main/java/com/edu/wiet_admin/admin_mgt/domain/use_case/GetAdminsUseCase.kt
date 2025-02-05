package com.edu.wiet_admin.admin_mgt.domain.use_case

import com.edu.wiet_admin.admin_auth.data.remote.responses.AdminData
import com.edu.wiet_admin.admin_mgt.domain.repository.AdminMgtRepository
import com.edu.wiet_admin.common.data.remote.Resource
import com.edu.wiet_admin.common.data.remote.WietApiResponse
import com.edu.wiet_admin.common.domain.RemoteUtils
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAdminsUseCase @Inject constructor(
    private val adminMgtRepository: AdminMgtRepository
) {
    operator fun invoke(
        searchQuery: String?,
        sortBy: String?,
        sortOrder: String?,
        page: Int?,
        limit: Int?
    ): Flow<Resource<WietApiResponse<List<AdminData>?>>> {
        return RemoteUtils.responseFlow {
            adminMgtRepository.getAdmins(searchQuery, sortBy, sortOrder, page, limit)
        }
    }
}
