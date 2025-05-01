package com.attendify_admin.academics.data


import com.attendify_admin.academics.data.dto.request.AddBranchRequest
import com.attendify_admin.academics.data.dto.request.UpdateBranchRequest
import com.attendify_admin.academics.domain.repository.BranchRepository
import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.data.remote.response_dto.Branch
import retrofit2.Response
import javax.inject.Inject

class BranchRepositoryImpl @Inject constructor(
    private val branchApi: BranchApi
) : BranchRepository {

    override suspend fun getBranches(searchQuery: String?): Response<AttendifyApiResponse<List<Branch?>>> =
        branchApi.getBranches(searchQuery)

    override suspend fun addBranch(requestBody: AddBranchRequest): Response<AttendifyApiResponse<Branch?>> =
        branchApi.addBranch(requestBody)

    override suspend fun updateBranch(requestBody: UpdateBranchRequest): Response<AttendifyApiResponse<Branch?>> =
        branchApi.updateBranch(requestBody)

    override suspend fun removeBranch(branchId: String): Response<AttendifyApiResponse<String?>> =
        branchApi.removeBranch(branchId)

    override suspend fun getBranchById(branchId: String): Response<AttendifyApiResponse<Branch?>> =
        branchApi.getBranchById(branchId)
}