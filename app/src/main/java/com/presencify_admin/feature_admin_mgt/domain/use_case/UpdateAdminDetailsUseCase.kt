package com.presencify_admin.feature_admin_mgt.domain.use_case

import com.presencify_admin.common.data.remote.Resource
import com.presencify_admin.common.domain.RemoteUtils
import com.presencify_admin.feature_admin_auth.data.remote.dto.responses.toAdmin
import com.presencify_admin.feature_admin_auth.domain.model.Admin
import com.presencify_admin.feature_admin_mgt.data.remote.dto.request.UpdateAdminDetailsRequestBody
import com.presencify_admin.feature_admin_mgt.domain.repository.AdminMgtRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class UpdateAdminDetailsUseCase @Inject constructor(
    private val adminMgtRepository: AdminMgtRepository
) {
    operator fun invoke(requestBody: UpdateAdminDetailsRequestBody): Flow<Resource<Admin?>> = flow {

        emit(Resource.Loading<Admin?>())

        val response = runCatching {
            adminMgtRepository.updateAdminDetails(requestBody)
        }

        response.onSuccess { response ->
            if (response.isSuccessful) {
                emit(Resource.Success(response.body()?.data?.toAdmin()))
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
