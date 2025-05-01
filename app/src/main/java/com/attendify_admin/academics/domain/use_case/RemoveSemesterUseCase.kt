package com.attendify_admin.academics.domain.use_case

import com.attendify_admin.academics.domain.repository.SemesterRepository
import com.attendify_admin.common.data.remote.Resource
import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.domain.RemoteUtils
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RemoveSemesterUseCase @Inject constructor(private val semesterRepository: SemesterRepository) {
    operator fun invoke(semesterId: String): Flow<Resource<AttendifyApiResponse<String?>>> {
        return RemoteUtils.responseFlow { semesterRepository.removeSemester(semesterId) }
    }
}