package com.presencify_admin.feature_admin_mgt.domain.use_case

import com.presencify_admin.common.data.remote.Resource
import com.presencify_admin.common.domain.RemoteUtils
import com.presencify_admin.feature_admin_auth.data.remote.dto.responses.toVerificationCode
import com.presencify_admin.feature_admin_auth.domain.model.VerificationCode
import com.presencify_admin.feature_admin_mgt.domain.repository.AdminMgtRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class SendVerificationCodeToEmailUseCase @Inject constructor(
    private val adminMgtRepository: AdminMgtRepository
) {
    operator fun invoke(): Flow<Resource<VerificationCode?>> = flow {

        emit(Resource.Loading<VerificationCode?>())

        val response = runCatching {
            adminMgtRepository.sendVerificationCodeToEmail()
        }

        response.onSuccess { response ->
            if (response.isSuccessful) {
                emit(Resource.Success(response.body()?.data?.toVerificationCode()))
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
