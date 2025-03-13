package com.edu.wiet_admin.academics.domain.use_case

import com.edu.wiet_admin.academics.data.dto.request.UpdateBranchRequest
import com.edu.wiet_admin.academics.domain.repository.BranchRepository
import com.edu.wiet_admin.common.data.remote.Resource
import com.edu.wiet_admin.common.data.remote.WietApiResponse
import com.edu.wiet_admin.common.data.remote.response_dto.Branch
import com.edu.wiet_admin.common.domain.RemoteUtils
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpdateBranchUseCase @Inject constructor(private val branchRepository: BranchRepository) {
    operator fun invoke(requestBody: UpdateBranchRequest): Flow<Resource<WietApiResponse<Branch?>>> {
        return RemoteUtils.responseFlow { branchRepository.updateBranch(requestBody) }
    }
}