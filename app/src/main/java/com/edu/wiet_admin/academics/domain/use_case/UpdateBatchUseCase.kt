package com.edu.wiet_admin.academics.domain.use_case

import com.edu.wiet_admin.academics.data.dto.request.UpdateBatchRequest
import com.edu.wiet_admin.academics.domain.repository.BatchRepository
import com.edu.wiet_admin.common.data.remote.Resource
import com.edu.wiet_admin.common.data.remote.WietApiResponse
import com.edu.wiet_admin.common.data.remote.response_dto.Batch
import com.edu.wiet_admin.common.domain.RemoteUtils
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpdateBatchUseCase @Inject constructor(
    private val batchRepository: BatchRepository
) {
    operator fun invoke(requestBody: UpdateBatchRequest): Flow<Resource<WietApiResponse<Batch?>>> {
        return RemoteUtils.responseFlow {
            batchRepository.updateBatch(requestBody)
        }
    }
}