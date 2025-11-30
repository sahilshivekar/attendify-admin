package com.presencify_admin.home.feature_academics.domain.use_case

import com.presencify_admin.common.data.remote.Resource
import com.presencify_admin.common.data.remote.dto.response.toDivision
import com.presencify_admin.common.domain.RemoteUtils
import com.presencify_admin.common.domain.model.Division
import com.presencify_admin.home.feature_academics.data.remote.dto.request.AddDivisionRequest
import com.presencify_admin.home.feature_academics.domain.repository.DivisionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class AddDivisionUseCase @Inject constructor(
    private val divisionRepository: DivisionRepository
) {
    operator fun invoke(requestBody: AddDivisionRequest): Flow<Resource<Division>> = flow {

        emit(Resource.Loading<Division>())

        val response = runCatching {
            divisionRepository.addDivision(requestBody)
        }

        response.onSuccess { response ->
            if (response.isSuccessful) {
                emit(Resource.Success(response.body()?.data?.toDivision()))
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
