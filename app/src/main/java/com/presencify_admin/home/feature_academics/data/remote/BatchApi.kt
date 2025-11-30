package com.presencify_admin.home.feature_academics.data.remote


import com.presencify_admin.common.data.remote.dto.response.PresencifyApiResponse
import com.presencify_admin.common.data.remote.dto.response.BatchDto
import com.presencify_admin.common.data.remote.dto.response.BatchListWithTotalCountDto
import com.presencify_admin.home.feature_academics.data.remote.dto.request.AddBatchRequest
import com.presencify_admin.home.feature_academics.data.remote.dto.request.UpdateBatchRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query


interface BatchApi {

    @GET("api/v1/batch/admin/get-batches")
    suspend fun getBatches(
        @Query("semesterNumber") semesterNumber: Int?,
        @Query("branchId") branchId: Int?,
        @Query("academicStartYear") academicStartYear: Int?,
        @Query("academicEndYear") academicEndYear: Int?,
        @Query("searchQuery") searchQuery: String?,
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 10,
        @Query("getAll") getAll: Boolean,
    ): Response<PresencifyApiResponse<BatchListWithTotalCountDto>>

    @POST("api/v1/batch/admin/add")
    suspend fun addBatch(@Body requestBody: AddBatchRequest): Response<PresencifyApiResponse<BatchDto?>>

    @PUT("api/v1/batch/admin/update")
    suspend fun updateBatch(@Body requestBody: UpdateBatchRequest): Response<PresencifyApiResponse<BatchDto?>>

    @DELETE("api/v1/batch/admin/remove")
    suspend fun removeBatch(@Query("id") batchId: Int): Response<PresencifyApiResponse<String?>>

    @GET("api/v1/batch/admin/get-batch-by-id")
    suspend fun getBatchById(@Query("batchId") batchId: Int): Response<PresencifyApiResponse<BatchDto?>>
}