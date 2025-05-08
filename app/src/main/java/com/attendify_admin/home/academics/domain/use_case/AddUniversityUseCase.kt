package com.attendify_admin.home.academics.domain.use_case

import com.attendify_admin.home.academics.data.dto.request.AddUniversityRequest
import com.attendify_admin.home.academics.domain.repository.UniversityRepository
import com.attendify_admin.common.data.remote.Resource
import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.data.remote.response_dto.University
import com.attendify_admin.common.domain.RemoteUtils
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AddUniversityUseCase @Inject constructor(private val universityRepository: UniversityRepository) {
    operator fun invoke(requestBody: AddUniversityRequest): Flow<Resource<AttendifyApiResponse<University?>>> {
        return RemoteUtils.responseFlow { universityRepository.addUniversity(requestBody) }
    }
}