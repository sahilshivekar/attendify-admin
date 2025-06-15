package com.attendify_admin.home.feature_academics.data

import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.data.remote.dto.response.BatchDto
import com.attendify_admin.home.feature_academics.data.dto.request.AddBatchRequest
import com.attendify_admin.home.feature_academics.data.dto.request.UpdateBatchRequest
import com.attendify_admin.home.feature_academics.domain.repository.BatchRepository
import retrofit2.Response
import javax.inject.Inject

class BatchRepositoryImpl @Inject constructor(
    private val batchApi: BatchApi
) : BatchRepository {

    override suspend fun getBatches(
        semesterNumber: Int?,
        branchId: Int?,
        academicStartYear: Int?,
        academicEndYear: Int?,
        searchQuery: String?,
        page: Int,
        limit: Int
    ): Response<AttendifyApiResponse<List<BatchDto>?>> =
        batchApi.getBatches(semesterNumber, branchId, academicStartYear, academicEndYear, searchQuery, page, limit)

    override suspend fun addBatch(requestBody: AddBatchRequest): Response<AttendifyApiResponse<BatchDto?>> =
        batchApi.addBatch(requestBody)

    override suspend fun updateBatch(requestBody: UpdateBatchRequest): Response<AttendifyApiResponse<BatchDto?>> =
        batchApi.updateBatch(requestBody)

    override suspend fun removeBatch(batchId: Int): Response<AttendifyApiResponse<String?>> =
        batchApi.removeBatch(batchId)

    override suspend fun getBatchById(batchId: Int): Response<AttendifyApiResponse<BatchDto?>> =
        batchApi.getBatchById(batchId)
}