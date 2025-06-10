package com.attendify_admin.home.academics.domain.repository


import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.data.remote.response_dto.Branch
import com.attendify_admin.home.academics.data.dto.request.AddBranchRequest
import com.attendify_admin.home.academics.data.dto.request.UpdateBranchRequest
import retrofit2.Response

interface BranchRepository {
    suspend fun getBranches(searchQuery: String?): Response<AttendifyApiResponse<List<Branch?>>>

    suspend fun addBranch(requestBody: AddBranchRequest): Response<AttendifyApiResponse<Branch?>>

    suspend fun updateBranch(requestBody: UpdateBranchRequest): Response<AttendifyApiResponse<Branch?>>

    suspend fun removeBranch(branchId: String): Response<AttendifyApiResponse<String?>>

    suspend fun getBranchById(branchId: String): Response<AttendifyApiResponse<Branch?>>
}