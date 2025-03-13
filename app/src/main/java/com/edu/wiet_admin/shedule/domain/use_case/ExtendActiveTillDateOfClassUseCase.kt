package com.edu.wiet_admin.shedule.domain.use_case

import com.edu.wiet_admin.common.data.remote.Resource
import com.edu.wiet_admin.common.data.remote.WietApiResponse
import com.edu.wiet_admin.common.data.remote.response_dto.Class
import com.edu.wiet_admin.common.domain.RemoteUtils
import com.edu.wiet_admin.shedule.data.dto.request.ExtendActiveTillDateRequest
import com.edu.wiet_admin.shedule.domain.repository.ClassRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ExtendActiveTillDateOfClassUseCase @Inject constructor(private val classRepository: ClassRepository) {
    operator fun invoke(requestBody: ExtendActiveTillDateRequest): Flow<Resource<WietApiResponse<Class>>> {
        return RemoteUtils.responseFlow { classRepository.extendActiveTillDateOfClass(requestBody) }
    }
}