package com.attendify_admin.home.users.domain.use_case

import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.data.remote.Resource
import com.attendify_admin.common.data.dto.response.Staff
import com.attendify_admin.common.domain.RemoteUtils
import com.attendify_admin.home.users.data.dto.request.RemoveImageRequest
import com.attendify_admin.home.users.domain.repository.StaffRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RemoveImageUseCase @Inject constructor(private val staffRepository: StaffRepository) {
    operator fun invoke(requestBody: RemoveImageRequest): Flow<Resource<AttendifyApiResponse<Staff>>> {
        return RemoteUtils.responseFlow { staffRepository.removeImage(requestBody) }
    }
}