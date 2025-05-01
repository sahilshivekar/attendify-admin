package com.attendify_admin.academics.domain.use_case

import com.attendify_admin.academics.data.dto.request.UpdateSchemeRequest
import com.attendify_admin.academics.domain.repository.SchemeRepository
import com.attendify_admin.common.data.remote.Resource
import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.data.remote.response_dto.Scheme
import com.attendify_admin.common.domain.RemoteUtils
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpdateSchemeUseCase @Inject constructor(private val schemeRepository: SchemeRepository) {
    operator fun invoke(requestBody: UpdateSchemeRequest): Flow<Resource<AttendifyApiResponse<Scheme?>>> {
        return RemoteUtils.responseFlow { schemeRepository.updateScheme(requestBody) }
    }
}