package com.attendify_admin.home.academics.data

import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.data.dto.response.Batch
import com.attendify_admin.home.academics.data.dto.request.AddBatchRequest
import com.attendify_admin.home.academics.data.dto.request.UpdateBatchRequest
import com.attendify_admin.home.academics.domain.repository.BatchRepository
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
    ): Response<AttendifyApiResponse<List<Batch?>>> =
        batchApi.getBatches(semesterNumber, branchId, academicStartYear, academicEndYear, searchQuery, page, limit)

    override suspend fun addBatch(requestBody: AddBatchRequest): Response<AttendifyApiResponse<Batch?>> =
        batchApi.addBatch(requestBody)

    override suspend fun updateBatch(requestBody: UpdateBatchRequest): Response<AttendifyApiResponse<Batch?>> =
        batchApi.updateBatch(requestBody)

    override suspend fun removeBatch(batchId: Int): Response<AttendifyApiResponse<String?>> =
        batchApi.removeBatch(batchId)

    override suspend fun getBatchById(batchId: Int): Response<AttendifyApiResponse<Batch?>> =
        batchApi.getBatchById(batchId)
}