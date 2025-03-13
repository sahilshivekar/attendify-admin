package com.edu.wiet_admin.shedule.domain.use_case

import com.edu.wiet_admin.common.data.remote.Resource
import com.edu.wiet_admin.common.data.remote.WietApiResponse
import com.edu.wiet_admin.common.data.remote.response_dto.Class
import com.edu.wiet_admin.common.domain.RemoteUtils
import com.edu.wiet_admin.shedule.domain.repository.ClassRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetClassByIdUseCase @Inject constructor(private val classRepository: ClassRepository) {
    operator fun invoke(classId: Int): Flow<Resource<WietApiResponse<Class>>> {
        return RemoteUtils.responseFlow { classRepository.getClassById(classId) }
    }
}