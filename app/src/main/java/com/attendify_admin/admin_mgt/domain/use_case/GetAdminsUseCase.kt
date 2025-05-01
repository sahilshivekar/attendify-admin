package com.attendify_admin.admin_mgt.domain.use_case

import com.attendify_admin.admin_auth.data.remote.responses.AdminData
import com.attendify_admin.admin_mgt.domain.repository.AdminMgtRepository
import com.attendify_admin.common.data.remote.Resource
import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.domain.RemoteUtils
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
    ): Flow<Resource<AttendifyApiResponse<List<AdminData>?>>> {
        return RemoteUtils.responseFlow {
            adminMgtRepository.getAdmins(searchQuery, sortBy, sortOrder, page, limit)
        }
    }
}
