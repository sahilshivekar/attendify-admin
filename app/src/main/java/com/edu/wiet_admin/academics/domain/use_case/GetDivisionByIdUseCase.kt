package com.edu.wiet_admin.academics.domain.use_case

import com.edu.wiet_admin.academics.domain.repository.DivisionRepository
import com.edu.wiet_admin.common.data.remote.Resource
import com.edu.wiet_admin.common.data.remote.WietApiResponse
import com.edu.wiet_admin.common.data.remote.response_dto.Division
import com.edu.wiet_admin.common.domain.RemoteUtils
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDivisionByIdUseCase @Inject constructor(private val divisionRepository: DivisionRepository) {
    operator fun invoke(divisionId: String): Flow<Resource<WietApiResponse<Division?>>> {
        return RemoteUtils.responseFlow { divisionRepository.getDivisionById(divisionId) }
    }
}