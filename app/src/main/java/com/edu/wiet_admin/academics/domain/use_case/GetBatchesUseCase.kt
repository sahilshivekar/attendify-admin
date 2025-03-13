package com.edu.wiet_admin.academics.domain.use_case

import com.edu.wiet_admin.academics.domain.repository.BatchRepository
import com.edu.wiet_admin.common.data.remote.Resource
import com.edu.wiet_admin.common.data.remote.WietApiResponse
import com.edu.wiet_admin.common.data.remote.response_dto.Batch
import com.edu.wiet_admin.common.domain.RemoteUtils
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBatchesUseCase @Inject constructor(
    private val batchRepository: BatchRepository
) {
    operator fun invoke(
        semesterNumber: Int?,
        branchId: String?,
        academicStartYear: Int?,
        academicEndYear: Int?,
        searchQuery: String?,
        page: Int,
        limit: Int
    ): Flow<Resource<WietApiResponse<List<Batch?>>>> {
        return RemoteUtils.responseFlow {
            batchRepository.getBatches(
                semesterNumber,
                branchId,
                academicStartYear,
                academicEndYear,
                searchQuery,
                page,
                limit
            )
        }
    }
}