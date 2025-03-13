package com.edu.wiet_admin.academics.data


import com.edu.wiet_admin.academics.data.dto.request.AddBatchRequest
import com.edu.wiet_admin.academics.data.dto.request.UpdateBatchRequest
import com.edu.wiet_admin.common.data.remote.WietApiResponse
import com.edu.wiet_admin.common.data.remote.response_dto.Batch
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query


interface BatchApi {

    @GET("api/v1/admin/get-batches")
    suspend fun getBatches(
        @Query("semesterNumber") semesterNumber: Int?,
        @Query("branchId") branchId: String?,
        @Query("academicStartYear") academicStartYear: Int?,
        @Query("academicEndYear") academicEndYear: Int?,
        @Query("searchQuery") searchQuery: String?,
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): Response<WietApiResponse<List<Batch?>>>

    @POST("api/v1/admin/add")
    suspend fun addBatch(@Body requestBody: AddBatchRequest): Response<WietApiResponse<Batch?>>

    @PUT("api/v1/admin/update")
    suspend fun updateBatch(@Body requestBody: UpdateBatchRequest): Response<WietApiResponse<Batch?>>

    @DELETE("api/v1/admin/remove")
    suspend fun removeBatch(@Query("id") batchId: String): Response<WietApiResponse<String?>>

    @GET("api/v1/admin/get-batch-by-id")
    suspend fun getBatchById(@Query("batchId") batchId: String): Response<WietApiResponse<Batch?>>
}