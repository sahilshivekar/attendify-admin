package com.attendify_admin.home.feature_academics.domain.repository

import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.data.remote.dto.response.BatchDto
import com.attendify_admin.home.feature_academics.data.dto.request.AddBatchRequest
import com.attendify_admin.home.feature_academics.data.dto.request.UpdateBatchRequest
import retrofit2.Response

interface BatchRepository {
    suspend fun getBatches(
        semesterNumber: Int?,
        branchId: Int?,
        academicStartYear: Int?,
        academicEndYear: Int?,
        searchQuery: String?,
        page: Int,
        limit: Int
    ): Response<AttendifyApiResponse<List<BatchDto>?>>

    suspend fun addBatch(requestBody: AddBatchRequest): Response<AttendifyApiResponse<BatchDto?>>

    suspend fun updateBatch(requestBody: UpdateBatchRequest): Response<AttendifyApiResponse<BatchDto?>>

    suspend fun removeBatch(batchId: Int): Response<AttendifyApiResponse<String?>>

    suspend fun getBatchById(batchId: Int): Response<AttendifyApiResponse<BatchDto?>>
}