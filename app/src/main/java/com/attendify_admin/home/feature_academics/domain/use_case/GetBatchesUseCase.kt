package com.attendify_admin.home.feature_academics.domain.use_case

import androidx.paging.PagingData
import androidx.paging.map
import com.attendify_admin.common.data.remote.Resource
import com.attendify_admin.common.data.remote.dto.response.toBatch
import com.attendify_admin.common.domain.RemoteUtils
import com.attendify_admin.common.domain.model.Batch
import com.attendify_admin.home.feature_academics.domain.repository.BatchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import java.io.IOException
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
