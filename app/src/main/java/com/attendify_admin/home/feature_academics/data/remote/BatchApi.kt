package com.attendify_admin.home.feature_academics.data.remote


import com.attendify_admin.common.data.remote.dto.response.AttendifyApiResponse
import com.attendify_admin.common.data.remote.dto.response.BatchDto
import com.attendify_admin.common.data.remote.dto.response.BatchListWithTotalCountDto
import com.attendify_admin.home.feature_academics.data.remote.dto.request.AddBatchRequest
import com.attendify_admin.home.feature_academics.data.remote.dto.request.UpdateBatchRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query


interface BatchApi {

    @GET("api/v1/admin/admin/get-batches")
    suspend fun getBatches(
        @Query("semesterNumber") semesterNumber: Int?,
        @Query("branchId") branchId: Int?,
        @Query("academicStartYear") academicStartYear: Int?,
        @Query("academicEndYear") academicEndYear: Int?,
        @Query("searchQuery") searchQuery: String?,
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): Response<AttendifyApiResponse<BatchListWithTotalCountDto>>

    @POST("api/v1/batch/admin/add")
    suspend fun addBatch(@Body requestBody: AddBatchRequest): Response<AttendifyApiResponse<BatchDto?>>

    @PUT("api/v1/batch/admin/update")
    suspend fun updateBatch(@Body requestBody: UpdateBatchRequest): Response<AttendifyApiResponse<BatchDto?>>

    @DELETE("api/v1/batch/admin/remove")
    suspend fun removeBatch(@Query("id") batchId: Int): Response<AttendifyApiResponse<String?>>

    @GET("api/v1/batch/admin/get-batch-by-id")
    suspend fun getBatchById(@Query("batchId") batchId: Int): Response<AttendifyApiResponse<BatchDto?>>
}