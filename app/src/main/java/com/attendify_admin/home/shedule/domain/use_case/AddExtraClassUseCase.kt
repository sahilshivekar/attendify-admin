package com.attendify_admin.home.shedule.domain.use_case

import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.data.remote.Resource
import com.attendify_admin.common.data.dto.response.Class
import com.attendify_admin.common.domain.RemoteUtils
import com.attendify_admin.home.shedule.data.dto.request.AddExtraClassRequest
import com.attendify_admin.home.shedule.domain.repository.ClassRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AddExtraClassUseCase @Inject constructor(private val classRepository: ClassRepository) {
    operator fun invoke(requestBody: AddExtraClassRequest): Flow<Resource<AttendifyApiResponse<Class>>> {
        return RemoteUtils.responseFlow { classRepository.addExtraClass(requestBody) }
    }
}