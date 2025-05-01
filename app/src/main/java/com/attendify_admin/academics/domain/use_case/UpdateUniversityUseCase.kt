package com.attendify_admin.academics.domain.use_case

import com.attendify_admin.academics.data.dto.request.UpdateUniversityRequest
import com.attendify_admin.academics.domain.repository.UniversityRepository
import com.attendify_admin.common.data.remote.Resource
import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.data.remote.response_dto.University
import com.attendify_admin.common.domain.RemoteUtils
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpdateUniversityUseCase @Inject constructor(private val universityRepository: UniversityRepository) {
    operator fun invoke(requestBody: UpdateUniversityRequest): Flow<Resource<AttendifyApiResponse<University?>>> {
        return RemoteUtils.responseFlow { universityRepository.updateUniversity(requestBody) }
    }
}