package com.attendify_admin.home.academics.domain.use_case

import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.data.remote.Resource
import com.attendify_admin.common.data.dto.response.Semester
import com.attendify_admin.common.domain.RemoteUtils
import com.attendify_admin.home.academics.data.dto.request.UpdateSemesterRequest
import com.attendify_admin.home.academics.domain.repository.SemesterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpdateSemesterUseCase @Inject constructor(private val semesterRepository: SemesterRepository) {
    operator fun invoke(requestBody: UpdateSemesterRequest): Flow<Resource<AttendifyApiResponse<Semester?>>> {
        return RemoteUtils.responseFlow { semesterRepository.updateSemester(requestBody) }
    }
}