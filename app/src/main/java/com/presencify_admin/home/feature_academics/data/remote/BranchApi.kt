package com.presencify_admin.home.feature_academics.data.remote

import com.presencify_admin.common.data.remote.dto.response.PresencifyApiResponse
import com.presencify_admin.common.data.remote.dto.response.BranchDto
import com.presencify_admin.home.feature_academics.data.remote.dto.request.AddBranchRequest
import com.presencify_admin.home.feature_academics.data.remote.dto.request.UpdateBranchRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query


interface BranchApi {

    @GET("api/v1/branch/admin/get-branches")
    suspend fun getBranches(@Query("searchQuery") searchQuery: String?): Response<PresencifyApiResponse<List<BranchDto>?>>

    @POST("api/v1/branch/admin/add")
    suspend fun addBranch(@Body requestBody: AddBranchRequest): Response<PresencifyApiResponse<BranchDto?>>

    @PUT("api/v1/branch/admin/update")
    suspend fun updateBranch(@Body requestBody: UpdateBranchRequest): Response<PresencifyApiResponse<BranchDto?>>

    @DELETE("api/v1/branch/admin/remove")
    suspend fun removeBranch(@Query("id") branchId: Int): Response<PresencifyApiResponse<String?>>

    @GET("api/v1/branch/admin/get-branch-by-id")
    suspend fun getBranchById(@Query("branchId") branchId: Int): Response<PresencifyApiResponse<BranchDto?>>
}