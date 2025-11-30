package com.presencify_admin.home.feature_users.domain.use_case

import com.presencify_admin.common.data.remote.Resource
import com.presencify_admin.common.data.remote.dto.response.toStaff
import com.presencify_admin.common.domain.RemoteUtils
import com.presencify_admin.common.domain.model.Staff
import com.presencify_admin.home.feature_users.data.remote.dto.request.UpdateStaffPasswordRequest
import com.presencify_admin.home.feature_users.domain.repository.StaffRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class UpdateStaffPasswordUseCase @Inject constructor(
    private val staffRepository: StaffRepository
) {
    operator fun invoke(requestBody: UpdateStaffPasswordRequest): Flow<Resource<Staff?>> = flow {
        emit(Resource.Loading())

        val response = runCatching {
            staffRepository.updateStaffPassword(requestBody)
        }

        response.onSuccess { response ->
            if (response.isSuccessful) {
                emit(Resource.Success(response.body()?.data?.toStaff()))
            } else {
                val errorMessage = RemoteUtils.getErrorMessage(response)
                emit(Resource.Error(message = errorMessage))
            }
        }

        response.onFailure { exception ->
            when (exception) {
                is IOException -> emit(Resource.Error(message = RemoteUtils.NETWORK_IO_ERROR_MESSAGE))
                else -> emit(Resource.Error(message = RemoteUtils.UNKNOWN_NETWORK_ERROR_MESSAGE))
            }
        }
    }
}
