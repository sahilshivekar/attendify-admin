package com.attendify_admin.home.feature_academics.domain.use_case

import com.attendify_admin.common.data.remote.Resource
import com.attendify_admin.common.data.remote.dto.response.toUniversity
import com.attendify_admin.common.domain.RemoteUtils
import com.attendify_admin.common.domain.model.University
import com.attendify_admin.home.feature_academics.data.dto.request.UpdateUniversityRequest
import com.attendify_admin.home.feature_academics.domain.repository.UniversityRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class UpdateUniversityUseCase @Inject constructor(
    private val universityRepository: UniversityRepository
) {
    operator fun invoke(requestBody: UpdateUniversityRequest): Flow<Resource<University?>> = flow {

        emit(Resource.Loading())

        val response = runCatching {
            universityRepository.updateUniversity(requestBody)
        }

        response.onSuccess { response ->
            if (response.isSuccessful) {
                emit(Resource.Success(response.body()?.data?.toUniversity()))
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
