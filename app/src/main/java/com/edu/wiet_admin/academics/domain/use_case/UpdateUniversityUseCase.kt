package com.edu.wiet_admin.academics.domain.use_case

import com.edu.wiet_admin.academics.data.dto.request.UpdateUniversityRequest
import com.edu.wiet_admin.academics.domain.repository.UniversityRepository
import com.edu.wiet_admin.common.data.remote.Resource
import com.edu.wiet_admin.common.data.remote.WietApiResponse
import com.edu.wiet_admin.common.data.remote.response_dto.University
import com.edu.wiet_admin.common.domain.RemoteUtils
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpdateUniversityUseCase @Inject constructor(private val universityRepository: UniversityRepository) {
    operator fun invoke(requestBody: UpdateUniversityRequest): Flow<Resource<WietApiResponse<University?>>> {
        return RemoteUtils.responseFlow { universityRepository.updateUniversity(requestBody) }
    }
}