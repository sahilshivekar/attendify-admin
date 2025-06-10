package com.attendify_admin.home.academics.domain.use_case

import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.data.remote.Resource
import com.attendify_admin.common.domain.RemoteUtils
import com.attendify_admin.home.academics.domain.repository.SchemeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RemoveSchemeUseCase @Inject constructor(private val schemeRepository: SchemeRepository) {
    operator fun invoke(schemeId: Int): Flow<Resource<AttendifyApiResponse<String?>>> {
        return RemoteUtils.responseFlow { schemeRepository.removeScheme(schemeId) }
    }
}