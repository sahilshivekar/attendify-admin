package com.attendify_admin.home.academics.domain.use_case

import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.data.remote.Resource
import com.attendify_admin.common.data.remote.response_dto.Division
import com.attendify_admin.common.domain.RemoteUtils
import com.attendify_admin.home.academics.data.dto.request.UpdateDivisionRequest
import com.attendify_admin.home.academics.domain.repository.DivisionRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpdateDivisionUseCase @Inject constructor(private val divisionRepository: DivisionRepository) {
    operator fun invoke(requestBody: UpdateDivisionRequest): Flow<Resource<AttendifyApiResponse<Division?>>> {
        return RemoteUtils.responseFlow { divisionRepository.updateDivision(requestBody) }
    }
}