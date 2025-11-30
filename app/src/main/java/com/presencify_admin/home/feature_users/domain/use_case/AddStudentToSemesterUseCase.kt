package com.presencify_admin.home.feature_users.domain.use_case

import com.presencify_admin.common.data.remote.Resource
import com.presencify_admin.common.data.remote.dto.response.toStudentSemester
import com.presencify_admin.common.domain.RemoteUtils
import com.presencify_admin.common.domain.model.StudentSemester
import com.presencify_admin.home.feature_users.data.remote.dto.request.AddStudentToSemesterRequest
import com.presencify_admin.home.feature_users.domain.repository.StudentRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class AddStudentToSemesterUseCase @Inject constructor(
    private val studentRepository: StudentRepository
) {
    operator fun invoke(
        requestBody: AddStudentToSemesterRequest
    ): Flow<Resource<StudentSemester?>> = flow {

        emit(Resource.Loading<StudentSemester?>())

        val response = runCatching {
            studentRepository.addStudentToSemester(requestBody)
        }

        response.onSuccess { response ->
            if (response.isSuccessful) {
                emit(Resource.Success(response.body()?.data?.toStudentSemester()))
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
