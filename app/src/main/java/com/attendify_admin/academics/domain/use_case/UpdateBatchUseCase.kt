package com.attendify_admin.academics.domain.use_case

import com.attendify_admin.academics.data.dto.request.UpdateBatchRequest
import com.attendify_admin.academics.domain.repository.BatchRepository
import com.attendify_admin.common.data.remote.Resource
import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.data.remote.response_dto.Batch
import com.attendify_admin.common.domain.RemoteUtils
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpdateBatchUseCase @Inject constructor(
    private val batchRepository: BatchRepository
) {
    operator fun invoke(requestBody: UpdateBatchRequest): Flow<Resource<AttendifyApiResponse<Batch?>>> {
        return RemoteUtils.responseFlow {
            batchRepository.updateBatch(requestBody)
        }
    }
}