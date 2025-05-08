package com.attendify_admin.home.academics.data

import com.attendify_admin.home.academics.data.dto.request.AddBranchRequest
import com.attendify_admin.home.academics.data.dto.request.UpdateBranchRequest
import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.data.remote.response_dto.Branch
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query


interface BranchApi {

    @GET("api/v1/branch/get-branches")
    suspend fun getBranches(@Query("searchQuery") searchQuery: String?): Response<AttendifyApiResponse<List<Branch?>>>

    @POST("api/v1/branch/add")
    suspend fun addBranch(@Body requestBody: AddBranchRequest): Response<AttendifyApiResponse<Branch?>>

    @PUT("api/v1/branch/update")
    suspend fun updateBranch(@Body requestBody: UpdateBranchRequest): Response<AttendifyApiResponse<Branch?>>

    @DELETE("api/v1/branch/remove")
    suspend fun removeBranch(@Query("id") branchId: String): Response<AttendifyApiResponse<String?>>

    @GET("api/v1/branch/get-branch-by-id")
    suspend fun getBranchById(@Query("branchId") branchId: String): Response<AttendifyApiResponse<Branch?>>
}