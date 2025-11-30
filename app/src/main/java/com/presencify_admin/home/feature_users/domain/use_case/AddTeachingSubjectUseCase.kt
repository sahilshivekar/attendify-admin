package com.presencify_admin.home.feature_users.domain.use_case

import com.presencify_admin.common.data.remote.Resource
import com.presencify_admin.common.data.remote.dto.response.toTeacherTeaches
import com.presencify_admin.common.domain.RemoteUtils
import com.presencify_admin.common.domain.model.TeacherTeaches
import com.presencify_admin.home.feature_users.data.remote.dto.request.AddTeachingSubjectRequest
import com.presencify_admin.home.feature_users.domain.repository.StaffRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class AddTeachingSubjectUseCase @Inject constructor(
    private val staffRepository: StaffRepository
) {
    operator fun invoke(
        requestBody: AddTeachingSubjectRequest
    ): Flow<Resource<TeacherTeaches?>> = flow {

        emit(Resource.Loading<TeacherTeaches?>())

        val response = runCatching {
            staffRepository.addTeachingSubject(requestBody)
        }

        response.onSuccess { response ->
            if (response.isSuccessful) {
                emit(Resource.Success(response.body()?.data?.toTeacherTeaches()))
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
