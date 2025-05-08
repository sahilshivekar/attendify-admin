package com.attendify_admin.home.academics.domain.use_case

import com.attendify_admin.home.academics.data.dto.request.AddBatchRequest
import com.attendify_admin.home.academics.domain.repository.BatchRepository
import com.attendify_admin.common.data.remote.Resource
import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.data.remote.response_dto.Batch
import com.attendify_admin.common.domain.RemoteUtils
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AddBatchUseCase @Inject constructor(
    private val batchRepository: BatchRepository
) {
    operator fun invoke(requestBody: AddBatchRequest): Flow<Resource<AttendifyApiResponse<Batch?>>> {
        return RemoteUtils.responseFlow {
            batchRepository.addBatch(requestBody)
        }
    }
}