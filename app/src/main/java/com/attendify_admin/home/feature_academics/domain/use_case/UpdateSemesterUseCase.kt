package com.attendify_admin.home.feature_academics.domain.use_case

import com.attendify_admin.common.data.remote.Resource
import com.attendify_admin.common.data.remote.dto.response.toSemester
import com.attendify_admin.common.domain.RemoteUtils
import com.attendify_admin.common.domain.model.Semester
import com.attendify_admin.home.feature_academics.data.remote.dto.request.UpdateSemesterRequest
import com.attendify_admin.home.feature_academics.domain.repository.SemesterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class UpdateSemesterUseCase @Inject constructor(
    private val semesterRepository: SemesterRepository
) {
    operator fun invoke(requestBody: UpdateSemesterRequest): Flow<Resource<Semester?>> = flow {

        emit(Resource.Loading())

        val response = runCatching {
            semesterRepository.updateSemester(requestBody)
        }

        response.onSuccess { response ->
            if (response.isSuccessful) {
                emit(Resource.Success(response.body()?.data?.toSemester()))
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
