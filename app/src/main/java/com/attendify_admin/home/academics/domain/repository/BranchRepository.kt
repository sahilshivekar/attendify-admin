package com.attendify_admin.home.academics.domain.repository


import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.data.dto.response.Branch
import com.attendify_admin.home.academics.data.dto.request.AddBranchRequest
import com.attendify_admin.home.academics.data.dto.request.UpdateBranchRequest
import retrofit2.Response

interface BranchRepository {
    suspend fun getBranches(searchQuery: String?): Response<AttendifyApiResponse<List<Branch?>>>

    suspend fun addBranch(requestBody: AddBranchRequest): Response<AttendifyApiResponse<Branch?>>

    suspend fun updateBranch(requestBody: UpdateBranchRequest): Response<AttendifyApiResponse<Branch?>>

    suspend fun removeBranch(branchId: Int): Response<AttendifyApiResponse<String?>>

    suspend fun getBranchById(branchId: Int): Response<AttendifyApiResponse<Branch?>>
}