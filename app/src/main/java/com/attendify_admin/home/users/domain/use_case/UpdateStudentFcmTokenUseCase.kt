package com.attendify_admin.home.users.domain.use_case

import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.data.remote.Resource
import com.attendify_admin.common.data.dto.response.StudentFcmToken
import com.attendify_admin.common.domain.RemoteUtils
import com.attendify_admin.home.users.data.dto.request.UpdateStudentFcmTokenRequest
import com.attendify_admin.home.users.domain.repository.StudentRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpdateStudentFcmTokenUseCase @Inject constructor(private val studentRepository: StudentRepository) {
    operator fun invoke(requestBody: UpdateStudentFcmTokenRequest): Flow<Resource<AttendifyApiResponse<StudentFcmToken?>>> {
        return RemoteUtils.responseFlow { studentRepository.updateStudentFcmToken(requestBody) }
    }
}