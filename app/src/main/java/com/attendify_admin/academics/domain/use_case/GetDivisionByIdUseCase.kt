package com.attendify_admin.academics.domain.use_case

import com.attendify_admin.academics.domain.repository.DivisionRepository
import com.attendify_admin.common.data.remote.Resource
import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.data.remote.response_dto.Division
import com.attendify_admin.common.domain.RemoteUtils
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDivisionByIdUseCase @Inject constructor(private val divisionRepository: DivisionRepository) {
    operator fun invoke(divisionId: String): Flow<Resource<AttendifyApiResponse<Division?>>> {
        return RemoteUtils.responseFlow { divisionRepository.getDivisionById(divisionId) }
    }
}