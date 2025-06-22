package com.attendify_admin.home.feature_academics.domain.repository

import androidx.paging.PagingData
import com.attendify_admin.common.data.remote.dto.response.AttendifyApiResponse
import com.attendify_admin.common.data.remote.dto.response.BatchDto
import com.attendify_admin.home.feature_academics.data.remote.dto.request.AddBatchRequest
import com.attendify_admin.home.feature_academics.data.remote.dto.request.UpdateBatchRequest
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface BatchRepository {
    fun getBatches(
        semesterNumber: Int?,
        branchId: Int?,
        academicStartYear: Int?,
        academicEndYear: Int?,
        searchQuery: String?,
    ): Flow<PagingData<BatchDto>>

    suspend fun addBatch(requestBody: AddBatchRequest): Response<AttendifyApiResponse<BatchDto?>>

    suspend fun updateBatch(requestBody: UpdateBatchRequest): Response<AttendifyApiResponse<BatchDto?>>

    suspend fun removeBatch(batchId: Int): Response<AttendifyApiResponse<String?>>

    suspend fun getBatchById(batchId: Int): Response<AttendifyApiResponse<BatchDto?>>
}