package com.edu.wiet_admin.users.domain.use_case

import com.edu.wiet_admin.common.data.remote.Resource
import com.edu.wiet_admin.common.data.remote.WietApiResponse
import com.edu.wiet_admin.common.data.remote.response_dto.Staff
import com.edu.wiet_admin.common.domain.RemoteUtils
import com.edu.wiet_admin.users.domain.repository.StaffRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

// StaffRepository Use Cases
class GetStaffUseCase @Inject constructor(private val staffRepository: StaffRepository) {
    operator fun invoke(
        searchQuery: String?,
        courseId: Int?,
        page: Int,
        limit: Int
    ): Flow<Resource<WietApiResponse<List<Staff>>>> {
        return RemoteUtils.responseFlow {
            staffRepository.getStaff(searchQuery, courseId, page, limit)
        }
    }
}