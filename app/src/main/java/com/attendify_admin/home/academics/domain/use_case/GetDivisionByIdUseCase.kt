package com.attendify_admin.home.academics.domain.use_case

import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.data.remote.Resource
import com.attendify_admin.common.data.dto.response.Division
import com.attendify_admin.common.domain.RemoteUtils
import com.attendify_admin.home.academics.domain.repository.DivisionRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDivisionByIdUseCase @Inject constructor(private val divisionRepository: DivisionRepository) {
    operator fun invoke(divisionId: Int): Flow<Resource<AttendifyApiResponse<Division?>>> {
        return RemoteUtils.responseFlow { divisionRepository.getDivisionById(divisionId) }
    }
}