package com.edu.wiet_admin.academics.domain.use_case

import com.edu.wiet_admin.academics.domain.repository.SchemeRepository
import com.edu.wiet_admin.common.data.remote.Resource
import com.edu.wiet_admin.common.data.remote.WietApiResponse
import com.edu.wiet_admin.common.domain.RemoteUtils
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RemoveSchemeUseCase @Inject constructor(private val schemeRepository: SchemeRepository) {
    operator fun invoke(schemeId: String): Flow<Resource<WietApiResponse<String?>>> {
        return RemoteUtils.responseFlow { schemeRepository.removeScheme(schemeId) }
    }
}