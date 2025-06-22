package com.attendify_admin.home.feature_academics.domain.repository


import com.attendify_admin.common.data.remote.dto.response.AttendifyApiResponse
import com.attendify_admin.common.data.remote.dto.response.BranchDto
import com.attendify_admin.home.feature_academics.data.remote.dto.request.AddBranchRequest
import com.attendify_admin.home.feature_academics.data.remote.dto.request.UpdateBranchRequest
import retrofit2.Response

interface BranchRepository {
    suspend fun getBranches(searchQuery: String?): Response<AttendifyApiResponse<List<BranchDto>?>>

    suspend fun addBranch(requestBody: AddBranchRequest): Response<AttendifyApiResponse<BranchDto?>>

    suspend fun updateBranch(requestBody: UpdateBranchRequest): Response<AttendifyApiResponse<BranchDto?>>

    suspend fun removeBranch(branchId: Int): Response<AttendifyApiResponse<String?>>

    suspend fun getBranchById(branchId: Int): Response<AttendifyApiResponse<BranchDto?>>
}