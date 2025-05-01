package com.attendify_admin.academics.domain.use_case

import com.attendify_admin.academics.domain.repository.BatchRepository
import com.attendify_admin.common.data.remote.Resource
import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.data.remote.response_dto.Batch
import com.attendify_admin.common.domain.RemoteUtils
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBatchesUseCase @Inject constructor(
    private val batchRepository: BatchRepository
) {
    operator fun invoke(
        semesterNumber: Int? = null,
        branchId: String? = null,
        academicStartYear: Int? = null,
        academicEndYear: Int? = null,
        searchQuery: String? = null,
        page: Int,
        limit: Int
    ): Flow<Resource<AttendifyApiResponse<List<Batch?>>>> {
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