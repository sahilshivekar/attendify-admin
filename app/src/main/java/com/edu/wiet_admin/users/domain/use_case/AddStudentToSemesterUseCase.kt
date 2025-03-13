package com.edu.wiet_admin.users.domain.use_case

import com.edu.wiet_admin.common.data.remote.Resource
import com.edu.wiet_admin.common.data.remote.WietApiResponse
import com.edu.wiet_admin.common.data.remote.response_dto.StudentSemester
import com.edu.wiet_admin.common.domain.RemoteUtils
import com.edu.wiet_admin.users.data.dto.request.AddStudentToSemesterRequest
import com.edu.wiet_admin.users.domain.repository.StudentRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AddStudentToSemesterUseCase @Inject constructor(private val studentRepository: StudentRepository) {
    operator fun invoke(requestBody: AddStudentToSemesterRequest): Flow<Resource<WietApiResponse<StudentSemester>>> {
        return RemoteUtils.responseFlow { studentRepository.addStudentToSemester(requestBody) }
    }
}