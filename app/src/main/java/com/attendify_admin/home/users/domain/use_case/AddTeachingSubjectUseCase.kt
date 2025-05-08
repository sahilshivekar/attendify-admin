package com.attendify_admin.home.users.domain.use_case

import com.attendify_admin.common.data.remote.Resource
import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.data.remote.response_dto.TeacherTeaches
import com.attendify_admin.common.domain.RemoteUtils
import com.attendify_admin.home.users.data.dto.request.AddTeachingSubjectRequest
import com.attendify_admin.home.users.domain.repository.StaffRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AddTeachingSubjectUseCase @Inject constructor(private val staffRepository: StaffRepository) {
    operator fun invoke(requestBody: AddTeachingSubjectRequest): Flow<Resource<AttendifyApiResponse<TeacherTeaches>>> {
        return RemoteUtils.responseFlow { staffRepository.addTeachingSubject(requestBody) }
    }
}