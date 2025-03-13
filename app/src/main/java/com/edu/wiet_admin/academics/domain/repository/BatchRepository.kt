package com.edu.wiet_admin.academics.domain.repository

import com.edu.wiet_admin.academics.data.dto.request.AddBatchRequest
import com.edu.wiet_admin.academics.data.dto.request.UpdateBatchRequest
import com.edu.wiet_admin.common.data.remote.WietApiResponse
import com.edu.wiet_admin.common.data.remote.response_dto.Batch
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
    ): Response<WietApiResponse<List<Batch?>>>

    suspend fun addBatch(requestBody: AddBatchRequest): Response<WietApiResponse<Batch?>>

    suspend fun updateBatch(requestBody: UpdateBatchRequest): Response<WietApiResponse<Batch?>>

    suspend fun removeBatch(batchId: String): Response<WietApiResponse<String?>>

    suspend fun getBatchById(batchId: String): Response<WietApiResponse<Batch?>>
}