package com.presencify_admin.home.feature_academics.data.repository


import com.presencify_admin.common.data.remote.dto.response.PresencifyApiResponse
import com.presencify_admin.common.data.remote.dto.response.BranchDto
import com.presencify_admin.home.feature_academics.data.remote.BranchApi
import com.presencify_admin.home.feature_academics.data.remote.dto.request.AddBranchRequest
import com.presencify_admin.home.feature_academics.data.remote.dto.request.UpdateBranchRequest
import com.presencify_admin.home.feature_academics.domain.repository.BranchRepository
import retrofit2.Response
import javax.inject.Inject

class BranchRepositoryImpl @Inject constructor(
    private val branchApi: BranchApi
) : BranchRepository {

    override suspend fun getBranches(searchQuery: String?): Response<PresencifyApiResponse<List<BranchDto>?>> =
        branchApi.getBranches(searchQuery)

    override suspend fun addBranch(requestBody: AddBranchRequest): Response<PresencifyApiResponse<BranchDto?>> =
        branchApi.addBranch(requestBody)

    override suspend fun updateBranch(requestBody: UpdateBranchRequest): Response<PresencifyApiResponse<BranchDto?>> =
        branchApi.updateBranch(requestBody)

    override suspend fun removeBranch(branchId: Int): Response<PresencifyApiResponse<String?>> =
        branchApi.removeBranch(branchId)

    override suspend fun getBranchById(branchId: Int): Response<PresencifyApiResponse<BranchDto?>> =
        branchApi.getBranchById(branchId)
}