package com.edu.wiet_admin.academics.data

import com.edu.wiet_admin.academics.data.dto.request.AddBatchRequest
import com.edu.wiet_admin.academics.data.dto.request.UpdateBatchRequest
import com.edu.wiet_admin.academics.domain.repository.BatchRepository
import com.edu.wiet_admin.common.data.remote.WietApiResponse
import com.edu.wiet_admin.common.data.remote.response_dto.Batch
import retrofit2.Response
import javax.inject.Inject

class BatchRepositoryImpl @Inject constructor(
    private val batchApi: BatchApi
) : BatchRepository {

    override suspend fun getBatches(
        semesterNumber: Int?,
        branchId: String?,
        academicStartYear: Int?,
        academicEndYear: Int?,
        searchQuery: String?,
        page: Int,
        limit: Int
    ): Response<WietApiResponse<List<Batch?>>> =
        batchApi.getBatches(semesterNumber, branchId, academicStartYear, academicEndYear, searchQuery, page, limit)

    override suspend fun addBatch(requestBody: AddBatchRequest): Response<WietApiResponse<Batch?>> =
        batchApi.addBatch(requestBody)

    override suspend fun updateBatch(requestBody: UpdateBatchRequest): Response<WietApiResponse<Batch?>> =
        batchApi.updateBatch(requestBody)

    override suspend fun removeBatch(batchId: String): Response<WietApiResponse<String?>> =
        batchApi.removeBatch(batchId)

    override suspend fun getBatchById(batchId: String): Response<WietApiResponse<Batch?>> =
        batchApi.getBatchById(batchId)
}