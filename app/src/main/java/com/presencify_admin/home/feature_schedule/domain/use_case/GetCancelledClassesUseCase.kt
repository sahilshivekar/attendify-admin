package com.presencify_admin.home.feature_schedule.domain.use_case

import androidx.paging.PagingData
import androidx.paging.map
import com.presencify_admin.common.data.remote.dto.response.toCancelledClass
import com.presencify_admin.common.domain.model.CancelledClass
import com.presencify_admin.home.feature_schedule.domain.repository.ClassRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetCancelledClassesUseCase @Inject constructor(
    private val classRepository: ClassRepository
) {
    operator fun invoke(
        divisionId: Int,
        batchId: Int,
        date: String
    ): Flow<PagingData<CancelledClass>> {
        return classRepository.getCancelledClasses(
            divisionId = divisionId,
            batchId = batchId,
            date = date
        ).map { pagingData ->
            pagingData.map { it.toCancelledClass() }
        }
    }
}
