package com.attendify_admin.home.feature_academics.data.repository


import com.attendify_admin.common.data.remote.dto.response.AttendifyApiResponse
import com.attendify_admin.common.data.remote.dto.response.BranchDto
import com.attendify_admin.home.feature_academics.data.remote.BranchApi
import com.attendify_admin.home.feature_academics.data.remote.dto.request.AddBranchRequest
import com.attendify_admin.home.feature_academics.data.remote.dto.request.UpdateBranchRequest
import com.attendify_admin.home.feature_academics.domain.repository.BranchRepository
import retrofit2.Response
import javax.inject.Inject

class BranchRepositoryImpl @Inject constructor(
    private val branchApi: BranchApi
) : BranchRepository {

    override suspend fun getBranches(searchQuery: String?): Response<AttendifyApiResponse<List<BranchDto>?>> =
        branchApi.getBranches(searchQuery)

    override suspend fun addBranch(requestBody: AddBranchRequest): Response<AttendifyApiResponse<BranchDto?>> =
        branchApi.addBranch(requestBody)

    override suspend fun updateBranch(requestBody: UpdateBranchRequest): Response<AttendifyApiResponse<BranchDto?>> =
        branchApi.updateBranch(requestBody)

    override suspend fun removeBranch(branchId: Int): Response<AttendifyApiResponse<String?>> =
        branchApi.removeBranch(branchId)

    override suspend fun getBranchById(branchId: Int): Response<AttendifyApiResponse<BranchDto?>> =
        branchApi.getBranchById(branchId)
}