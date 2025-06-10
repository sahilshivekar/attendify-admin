package com.attendify_admin.home.academics.domain.use_case

import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.data.remote.Resource
import com.attendify_admin.common.data.dto.response.University
import com.attendify_admin.common.domain.RemoteUtils
import com.attendify_admin.home.academics.domain.repository.UniversityRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUniversityByIdUseCase @Inject constructor(private val universityRepository: UniversityRepository) {
    operator fun invoke(universityId: Int): Flow<Resource<AttendifyApiResponse<University?>>> {
        return RemoteUtils.responseFlow { universityRepository.getUniversityById(universityId) }
    }
}