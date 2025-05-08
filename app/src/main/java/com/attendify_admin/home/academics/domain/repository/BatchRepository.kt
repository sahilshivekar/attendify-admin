package com.attendify_admin.home.academics.domain.repository

import com.attendify_admin.home.academics.data.dto.request.AddBatchRequest
import com.attendify_admin.home.academics.data.dto.request.UpdateBatchRequest
import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.data.remote.response_dto.Batch
import retrofit2.Response

interface BatchRepository {
    suspend fun getBatches(
        semesterNumber: Int?,
        branchId: String?,
        academicStartYear: Int?,
        academicEndYear: Int?,
        searchQuery: String?,
        page: Int,
        limit: Int
    ): Response<AttendifyApiResponse<List<Batch?>>>

    suspend fun addBatch(requestBody: AddBatchRequest): Response<AttendifyApiResponse<Batch?>>

    suspend fun updateBatch(requestBody: UpdateBatchRequest): Response<AttendifyApiResponse<Batch?>>

    suspend fun removeBatch(batchId: String): Response<AttendifyApiResponse<String?>>

    suspend fun getBatchById(batchId: String): Response<AttendifyApiResponse<Batch?>>
}