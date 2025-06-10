package com.attendify_admin.home.users.domain.use_case

import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.data.remote.Resource
import com.attendify_admin.common.data.remote.response_dto.StudentDivision
import com.attendify_admin.common.domain.RemoteUtils
import com.attendify_admin.home.users.data.dto.request.ChangeStudentDivisionRequest
import com.attendify_admin.home.users.domain.repository.StudentRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ChangeStudentDivisionUseCase @Inject constructor(private val studentRepository: StudentRepository) {
    operator fun invoke(requestBody: ChangeStudentDivisionRequest): Flow<Resource<AttendifyApiResponse<StudentDivision>>> {
        return RemoteUtils.responseFlow { studentRepository.changeStudentDivision(requestBody) }
    }
}