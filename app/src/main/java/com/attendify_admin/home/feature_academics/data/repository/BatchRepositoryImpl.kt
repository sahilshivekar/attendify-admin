package com.attendify_admin.home.feature_academics.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.attendify_admin.common.data.remote.dto.response.AttendifyApiResponse
import com.attendify_admin.common.data.remote.dto.response.BatchDto
import com.attendify_admin.home.feature_academics.data.remote.dto.request.AddBatchRequest
import com.attendify_admin.home.feature_academics.data.remote.dto.request.UpdateBatchRequest
import com.attendify_admin.home.feature_academics.data.remote.BatchApi
import com.attendify_admin.home.feature_academics.domain.repository.BatchRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class BatchRepositoryImpl @Inject constructor(
    private val batchApi: BatchApi,
) : BatchRepository {

    override fun getBatches(
        semesterNumber: Int?,
        branchId: Int?,
        academicStartYear: Int?,
        academicEndYear: Int?,
        searchQuery: String?,
    ): Flow<PagingData<BatchDto>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                GetBatchesPagingSource(
                    batchApi = batchApi,
                    semesterNumber = semesterNumber,
                    branchId = branchId,
                    academicStartYear = academicStartYear,
                    academicEndYear = academicEndYear,
                    searchQuery = searchQuery
                )
            }
        ).flow
    }

    override suspend fun addBatch(requestBody: AddBatchRequest): Response<AttendifyApiResponse<BatchDto?>> =
        batchApi.addBatch(requestBody)

    override suspend fun updateBatch(requestBody: UpdateBatchRequest): Response<AttendifyApiResponse<BatchDto?>> =
        batchApi.updateBatch(requestBody)

    override suspend fun removeBatch(batchId: Int): Response<AttendifyApiResponse<String?>> =
        batchApi.removeBatch(batchId)

    override suspend fun getBatchById(batchId: Int): Response<AttendifyApiResponse<BatchDto?>> =
        batchApi.getBatchById(batchId)
}