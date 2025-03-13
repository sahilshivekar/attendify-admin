package com.edu.wiet_admin.users.domain.use_case

import com.edu.wiet_admin.common.data.remote.Resource
import com.edu.wiet_admin.common.data.remote.WietApiResponse
import com.edu.wiet_admin.common.domain.RemoteUtils
import com.edu.wiet_admin.users.data.dto.request.RemoveStudentFromSemesterRequest
import com.edu.wiet_admin.users.domain.repository.StudentRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RemoveStudentFromSemesterUseCase @Inject constructor(private val studentRepository: StudentRepository) {
    operator fun invoke(requestBody: RemoveStudentFromSemesterRequest): Flow<Resource<WietApiResponse<Unit>>> {
        return RemoteUtils.responseFlow { studentRepository.removeStudentFromSemester(requestBody) }
    }
}