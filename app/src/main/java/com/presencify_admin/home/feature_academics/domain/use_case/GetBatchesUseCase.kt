package com.presencify_admin.home.feature_academics.domain.use_case

import androidx.paging.PagingData
import androidx.paging.map
import com.presencify_admin.common.data.remote.dto.response.toBatch
import com.presencify_admin.common.domain.model.Batch
import com.presencify_admin.home.feature_academics.domain.repository.BatchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class GetBatchesUseCase @Inject constructor(
    private val batchRepository: BatchRepository,
) {
    operator fun invoke(
        semesterNumber: Int? = null,
        branchId: Int? = null,
        academicStartYear: Int? = null,
        academicEndYear: Int? = null,
        searchQuery: String? = null
    ): Flow<PagingData<Batch>> {
        return batchRepository.getBatches(
            semesterNumber,
            branchId,
            academicStartYear,
            academicEndYear,
            searchQuery
        ).map { pagingData ->
            pagingData.map { it.toBatch() }
        }
    }
}
