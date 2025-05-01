package com.attendify_admin.academics.domain.use_case

import com.attendify_admin.academics.domain.repository.BranchRepository
import com.attendify_admin.common.data.remote.Resource
import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.data.remote.response_dto.Branch
import com.attendify_admin.common.domain.RemoteUtils
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

// BranchRepository Use Cases
class GetBranchesUseCase @Inject constructor(private val branchRepository: BranchRepository) {
    operator fun invoke(searchQuery: String?): Flow<Resource<AttendifyApiResponse<List<Branch?>>>> {
        return RemoteUtils.responseFlow { branchRepository.getBranches(searchQuery) }
    }
}