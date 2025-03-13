package com.edu.wiet_admin.academics.data

import com.edu.wiet_admin.academics.data.dto.request.AddBranchRequest
import com.edu.wiet_admin.academics.data.dto.request.UpdateBranchRequest
import com.edu.wiet_admin.common.data.remote.WietApiResponse
import com.edu.wiet_admin.common.data.remote.response_dto.Branch
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query


interface BranchApi {

    @GET("api/v1/admin/get-branches")
    suspend fun getBranches(@Query("searchQuery") searchQuery: String?): Response<WietApiResponse<List<Branch?>>>

    @POST("api/v1/admin/add")
    suspend fun addBranch(@Body requestBody: AddBranchRequest): Response<WietApiResponse<Branch?>>

    @PUT("api/v1/admin/update")
    suspend fun updateBranch(@Body requestBody: UpdateBranchRequest): Response<WietApiResponse<Branch?>>

    @DELETE("api/v1/admin/remove")
    suspend fun removeBranch(@Query("id") branchId: String): Response<WietApiResponse<String?>>

    @GET("api/v1/admin/get-branch-by-id")
    suspend fun getBranchById(@Query("branchId") branchId: String): Response<WietApiResponse<Branch?>>
}