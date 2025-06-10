package com.attendify_admin.home.academics.domain.use_case

import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.data.remote.Resource
import com.attendify_admin.common.domain.RemoteUtils
import com.attendify_admin.home.academics.domain.repository.DivisionRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RemoveDivisionUseCase @Inject constructor(private val divisionRepository: DivisionRepository) {
    operator fun invoke(divisionId: String): Flow<Resource<AttendifyApiResponse<String?>>> {
        return RemoteUtils.responseFlow { divisionRepository.removeDivision(divisionId) }
    }
}