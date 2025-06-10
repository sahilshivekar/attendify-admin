package com.attendify_admin.home.academics.domain.use_case

import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.data.remote.Resource
import com.attendify_admin.common.domain.RemoteUtils
import com.attendify_admin.home.academics.domain.repository.BranchRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RemoveBranchUseCase @Inject constructor(private val branchRepository: BranchRepository) {
    operator fun invoke(branchId: String): Flow<Resource<AttendifyApiResponse<String?>>> {
        return RemoteUtils.responseFlow { branchRepository.removeBranch(branchId) }
    }
}