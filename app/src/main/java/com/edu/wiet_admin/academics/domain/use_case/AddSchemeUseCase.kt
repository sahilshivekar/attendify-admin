package com.edu.wiet_admin.academics.domain.use_case

import com.edu.wiet_admin.academics.data.dto.request.AddSchemeRequest
import com.edu.wiet_admin.academics.domain.repository.SchemeRepository
import com.edu.wiet_admin.common.data.remote.Resource
import com.edu.wiet_admin.common.data.remote.WietApiResponse
import com.edu.wiet_admin.common.data.remote.response_dto.Scheme
import com.edu.wiet_admin.common.domain.RemoteUtils
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AddSchemeUseCase @Inject constructor(private val schemeRepository: SchemeRepository) {
    operator fun invoke(requestBody: AddSchemeRequest): Flow<Resource<WietApiResponse<Scheme?>>> {
        return RemoteUtils.responseFlow { schemeRepository.addScheme(requestBody) }
    }
}