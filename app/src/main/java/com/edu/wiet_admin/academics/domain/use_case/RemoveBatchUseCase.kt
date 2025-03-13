package com.edu.wiet_admin.academics.domain.use_case

import com.edu.wiet_admin.academics.domain.repository.BatchRepository
import com.edu.wiet_admin.common.data.remote.Resource
import com.edu.wiet_admin.common.data.remote.WietApiResponse
import com.edu.wiet_admin.common.domain.RemoteUtils
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RemoveBatchUseCase @Inject constructor(
    private val batchRepository: BatchRepository
) {
    operator fun invoke(batchId: String): Flow<Resource<WietApiResponse<String?>>> {
        return RemoteUtils.responseFlow {
            batchRepository.removeBatch(batchId)
        }
    }
}