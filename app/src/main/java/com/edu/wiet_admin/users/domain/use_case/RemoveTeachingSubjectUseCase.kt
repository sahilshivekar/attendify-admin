package com.edu.wiet_admin.users.domain.use_case

import com.edu.wiet_admin.common.data.remote.Resource
import com.edu.wiet_admin.common.data.remote.WietApiResponse
import com.edu.wiet_admin.common.domain.RemoteUtils
import com.edu.wiet_admin.users.domain.repository.StaffRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class RemoveTeachingSubjectUseCase @Inject constructor(private val staffRepository: StaffRepository) {
    operator fun invoke(teacherSubjectId: Int): Flow<Resource<WietApiResponse<Unit>>> {
        return RemoteUtils.responseFlow { staffRepository.removeTeachingSubject(teacherSubjectId) }
    }
}