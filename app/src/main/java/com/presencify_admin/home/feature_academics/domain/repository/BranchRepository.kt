package com.presencify_admin.home.feature_academics.domain.repository


import com.presencify_admin.common.data.remote.dto.response.PresencifyApiResponse
import com.presencify_admin.common.data.remote.dto.response.BranchDto
import com.presencify_admin.home.feature_academics.data.remote.dto.request.AddBranchRequest
import com.presencify_admin.home.feature_academics.data.remote.dto.request.UpdateBranchRequest
import retrofit2.Response

interface BranchRepository {
    suspend fun getBranches(searchQuery: String?): Response<PresencifyApiResponse<List<BranchDto>?>>

    suspend fun addBranch(requestBody: AddBranchRequest): Response<PresencifyApiResponse<BranchDto?>>

    suspend fun updateBranch(requestBody: UpdateBranchRequest): Response<PresencifyApiResponse<BranchDto?>>

    suspend fun removeBranch(branchId: Int): Response<PresencifyApiResponse<String?>>

    suspend fun getBranchById(branchId: Int): Response<PresencifyApiResponse<BranchDto?>>
}