package com.attendify_admin.academics.domain.use_case

import com.attendify_admin.academics.domain.repository.UniversityRepository
import com.attendify_admin.common.data.remote.Resource
import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.domain.RemoteUtils
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RemoveUniversityUseCase @Inject constructor(private val universityRepository: UniversityRepository) {
    operator fun invoke(universityId: String): Flow<Resource<AttendifyApiResponse<String?>>> {
        return RemoteUtils.responseFlow { universityRepository.removeUniversity(universityId) }
    }
}