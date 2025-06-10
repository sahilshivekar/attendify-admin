package com.attendify_admin.home.shedule.domain.use_case

import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.data.remote.Resource
import com.attendify_admin.common.data.dto.response.CancelledClass
import com.attendify_admin.common.domain.RemoteUtils
import com.attendify_admin.home.shedule.domain.repository.ClassRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCancelledClassesUseCase @Inject constructor(private val classRepository: ClassRepository) {
    operator fun invoke(
        divisionId: Int,
        batchId: Int,
        date: String, // As per your API/Repo; confirm if this should be a String for date format
        page: Int,
        limit: Int
    ): Flow<Resource<AttendifyApiResponse<CancelledClass>>> {
        return RemoteUtils.responseFlow {
            classRepository.getCancelledClasses(
                divisionId,
                batchId,
                date,
                page,
                limit
            )
        }
    }
}