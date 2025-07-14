package com.attendify_admin.home.feature_academics.domain.use_case

import com.attendify_admin.common.data.remote.Resource
import com.attendify_admin.common.data.remote.dto.response.toScheme
import com.attendify_admin.common.domain.RemoteUtils
import com.attendify_admin.common.domain.model.Scheme
import com.attendify_admin.home.feature_academics.domain.repository.SchemeRepository
import kotlinx.collections.immutable.ImmutableList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetSchemesUseCase @Inject constructor(
    private val schemeRepository: SchemeRepository
) {
    operator fun invoke(searchQuery: String?): Flow<Resource<List<Scheme>>> = flow {

        emit(Resource.Loading())

        val response = runCatching {
            schemeRepository.getSchemes(searchQuery)
        }

        response.onSuccess { response ->
            if (response.isSuccessful) {
                emit(Resource.Success(response.body()?.data?.map{it.toScheme()}))
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
