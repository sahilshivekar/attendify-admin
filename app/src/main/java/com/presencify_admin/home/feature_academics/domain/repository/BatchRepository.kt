package com.presencify_admin.home.feature_academics.domain.repository

import androidx.paging.PagingData
import com.presencify_admin.common.data.remote.dto.response.PresencifyApiResponse
import com.presencify_admin.common.data.remote.dto.response.BatchDto
import com.presencify_admin.common.data.remote.dto.response.BatchListWithTotalCountDto
import com.presencify_admin.home.feature_academics.data.remote.dto.request.AddBatchRequest
import com.presencify_admin.home.feature_academics.data.remote.dto.request.UpdateBatchRequest
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

    suspend fun getAllBatches(
        semesterNumber: Int?,
        branchId: Int?,
        academicStartYear: Int?,
        academicEndYear: Int?,
        searchQuery: String?
    ): Response<PresencifyApiResponse<BatchListWithTotalCountDto>>

    suspend fun addBatch(requestBody: AddBatchRequest): Response<PresencifyApiResponse<BatchDto?>>

    suspend fun updateBatch(requestBody: UpdateBatchRequest): Response<PresencifyApiResponse<BatchDto?>>

    suspend fun removeBatch(batchId: Int): Response<PresencifyApiResponse<String?>>

    suspend fun getBatchById(batchId: Int): Response<PresencifyApiResponse<BatchDto?>>
}