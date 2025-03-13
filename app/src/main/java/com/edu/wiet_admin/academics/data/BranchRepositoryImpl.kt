package com.edu.wiet_admin.academics.data


import com.edu.wiet_admin.academics.data.dto.request.AddBranchRequest
import com.edu.wiet_admin.academics.data.dto.request.UpdateBranchRequest
import com.edu.wiet_admin.academics.domain.repository.BranchRepository
import com.edu.wiet_admin.common.data.remote.WietApiResponse
import com.edu.wiet_admin.common.data.remote.response_dto.Branch
import retrofit2.Response
import javax.inject.Inject

class BranchRepositoryImpl @Inject constructor(
    private val branchApi: BranchApi
) : BranchRepository {

    override suspend fun getBranches(searchQuery: String?): Response<WietApiResponse<List<Branch?>>> =
        branchApi.getBranches(searchQuery)

    override suspend fun addBranch(requestBody: AddBranchRequest): Response<WietApiResponse<Branch?>> =
        branchApi.addBranch(requestBody)

    override suspend fun updateBranch(requestBody: UpdateBranchRequest): Response<WietApiResponse<Branch?>> =
        branchApi.updateBranch(requestBody)

    override suspend fun removeBranch(branchId: String): Response<WietApiResponse<String?>> =
        branchApi.removeBranch(branchId)

    override suspend fun getBranchById(branchId: String): Response<WietApiResponse<Branch?>> =
        branchApi.getBranchById(branchId)
}