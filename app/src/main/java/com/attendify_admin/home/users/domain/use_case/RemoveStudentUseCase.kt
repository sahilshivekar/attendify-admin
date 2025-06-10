package com.attendify_admin.home.users.domain.use_case

import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.data.remote.Resource
import com.attendify_admin.common.domain.RemoteUtils
import com.attendify_admin.home.users.data.dto.request.RemoveStudentRequest
import com.attendify_admin.home.users.domain.repository.StudentRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RemoveStudentUseCase @Inject constructor(private val studentRepository: StudentRepository) {
    operator fun invoke(requestBody: RemoveStudentRequest): Flow<Resource<AttendifyApiResponse<Unit>>> {
        return RemoteUtils.responseFlow { studentRepository.removeStudent(requestBody) }
    }
}