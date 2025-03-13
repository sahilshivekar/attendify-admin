package com.edu.wiet_admin.users.domain.use_case

import com.edu.wiet_admin.common.data.remote.Resource
import com.edu.wiet_admin.common.data.remote.WietApiResponse
import com.edu.wiet_admin.common.data.remote.response_dto.TeacherTeaches
import com.edu.wiet_admin.common.domain.RemoteUtils
import com.edu.wiet_admin.users.data.dto.request.AddTeachingSubjectRequest
import com.edu.wiet_admin.users.domain.repository.StaffRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AddTeachingSubjectUseCase @Inject constructor(private val staffRepository: StaffRepository) {
    operator fun invoke(requestBody: AddTeachingSubjectRequest): Flow<Resource<WietApiResponse<TeacherTeaches>>> {
        return RemoteUtils.responseFlow { staffRepository.addTeachingSubject(requestBody) }
    }
}