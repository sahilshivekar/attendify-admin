package com.attendify_admin.home.shedule.domain.use_case

import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.data.remote.Resource
import com.attendify_admin.common.domain.RemoteUtils
import com.attendify_admin.home.shedule.data.dto.request.CancelClassRequest
import com.attendify_admin.home.shedule.domain.repository.ClassRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CancelClassUseCase @Inject constructor(private val classRepository: ClassRepository) {
    operator fun invoke(requestBody: CancelClassRequest): Flow<Resource<AttendifyApiResponse<Unit>>> {
        return RemoteUtils.responseFlow { classRepository.cancelClass(requestBody) }
    }
}