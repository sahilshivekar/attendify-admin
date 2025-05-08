package com.attendify_admin.home.academics.domain.use_case

import com.attendify_admin.home.academics.domain.repository.UniversityRepository
import com.attendify_admin.common.data.remote.Resource
import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.data.remote.response_dto.University
import com.attendify_admin.common.domain.RemoteUtils
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

// UniversityRepository Use Cases
class GetUniversitiesUseCase @Inject constructor(private val universityRepository: UniversityRepository) {
    operator fun invoke(): Flow<Resource<AttendifyApiResponse<List<University?>>>> {
        return RemoteUtils.responseFlow { universityRepository.getUniversities() }
    }
}