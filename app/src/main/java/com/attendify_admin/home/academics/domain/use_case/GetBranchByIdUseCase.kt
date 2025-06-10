package com.attendify_admin.home.academics.domain.use_case

import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.data.remote.Resource
import com.attendify_admin.common.data.remote.response_dto.Branch
import com.attendify_admin.common.domain.RemoteUtils
import com.attendify_admin.home.academics.domain.repository.BranchRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBranchByIdUseCase @Inject constructor(private val branchRepository: BranchRepository) {
    operator fun invoke(branchId: Int): Flow<Resource<AttendifyApiResponse<Branch?>>> {
        return RemoteUtils.responseFlow { branchRepository.getBranchById(branchId) }
    }
}