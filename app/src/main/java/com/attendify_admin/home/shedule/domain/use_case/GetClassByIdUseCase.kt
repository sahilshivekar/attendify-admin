package com.attendify_admin.home.shedule.domain.use_case

import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.data.remote.Resource
import com.attendify_admin.common.data.remote.response_dto.Class
import com.attendify_admin.common.domain.RemoteUtils
import com.attendify_admin.home.shedule.domain.repository.ClassRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetClassByIdUseCase @Inject constructor(private val classRepository: ClassRepository) {
    operator fun invoke(classId: Int): Flow<Resource<AttendifyApiResponse<Class>>> {
        return RemoteUtils.responseFlow { classRepository.getClassById(classId) }
    }
}


