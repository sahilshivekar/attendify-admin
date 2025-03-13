package com.edu.wiet_admin.academics.domain.use_case

import com.edu.wiet_admin.academics.data.dto.request.AddDivisionRequest
import com.edu.wiet_admin.academics.domain.repository.DivisionRepository
import com.edu.wiet_admin.common.data.remote.Resource
import com.edu.wiet_admin.common.data.remote.WietApiResponse
import com.edu.wiet_admin.common.data.remote.response_dto.Division
import com.edu.wiet_admin.common.domain.RemoteUtils
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AddDivisionUseCase @Inject constructor(private val divisionRepository: DivisionRepository) {
    operator fun invoke(requestBody: AddDivisionRequest): Flow<Resource<WietApiResponse<Division?>>> {
        return RemoteUtils.responseFlow { divisionRepository.addDivision(requestBody) }
    }
}