package com.attendify_admin.home.users.domain.use_case

import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.data.remote.Resource
import com.attendify_admin.common.data.dto.response.StudentBatch
import com.attendify_admin.common.domain.RemoteUtils
import com.attendify_admin.home.users.data.dto.request.ChangeStudentBatchRequest
import com.attendify_admin.home.users.domain.repository.StudentRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ChangeStudentBatchUseCase @Inject constructor(private val studentRepository: StudentRepository) {
    operator fun invoke(requestBody: ChangeStudentBatchRequest): Flow<Resource<AttendifyApiResponse<StudentBatch>>> {
        return RemoteUtils.responseFlow { studentRepository.changeStudentBatch(requestBody) }
    }
}