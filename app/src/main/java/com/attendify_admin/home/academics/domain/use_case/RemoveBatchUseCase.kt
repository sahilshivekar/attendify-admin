package com.attendify_admin.home.academics.domain.use_case

import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.data.remote.Resource
import com.attendify_admin.common.domain.RemoteUtils
import com.attendify_admin.home.academics.domain.repository.BatchRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RemoveBatchUseCase @Inject constructor(
    private val batchRepository: BatchRepository
) {
    operator fun invoke(batchId: String): Flow<Resource<AttendifyApiResponse<String?>>> {
        return RemoteUtils.responseFlow {
            batchRepository.removeBatch(batchId)
        }
    }
}