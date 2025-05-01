package com.attendify_admin.users.domain.use_case

import com.attendify_admin.common.data.remote.Resource
import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.domain.RemoteUtils
import com.attendify_admin.users.domain.repository.StaffRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class RemoveTeachingSubjectUseCase @Inject constructor(private val staffRepository: StaffRepository) {
    operator fun invoke(teacherSubjectId: Int): Flow<Resource<AttendifyApiResponse<Unit>>> {
        return RemoteUtils.responseFlow { staffRepository.removeTeachingSubject(teacherSubjectId) }
    }
}