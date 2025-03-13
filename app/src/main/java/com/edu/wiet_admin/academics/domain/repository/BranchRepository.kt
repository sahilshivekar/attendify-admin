package com.edu.wiet_admin.academics.domain.repository


import com.edu.wiet_admin.academics.data.dto.request.AddBranchRequest
import com.edu.wiet_admin.academics.data.dto.request.UpdateBranchRequest
import com.edu.wiet_admin.common.data.remote.WietApiResponse
import com.edu.wiet_admin.common.data.remote.response_dto.Branch
import retrofit2.Response

interface BranchRepository {
    suspend fun getBranches(searchQuery: String?): Response<WietApiResponse<List<Branch?>>>

    suspend fun addBranch(requestBody: AddBranchRequest): Response<WietApiResponse<Branch?>>

    suspend fun updateBranch(requestBody: UpdateBranchRequest): Response<WietApiResponse<Branch?>>

    suspend fun removeBranch(branchId: String): Response<WietApiResponse<String?>>

    suspend fun getBranchById(branchId: String): Response<WietApiResponse<Branch?>>
}