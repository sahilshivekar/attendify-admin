package com.attendify_admin.home.feature_users.domain.use_case

import android.util.Log
import com.attendify_admin.common.data.remote.Resource
import com.attendify_admin.common.data.remote.dto.response.toStudent
import com.attendify_admin.common.domain.RemoteUtils
import com.attendify_admin.common.domain.model.Student
import com.attendify_admin.home.feature_users.domain.repository.StudentRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetStudentDetailsByIdUseCase @Inject constructor(
    private val studentRepository: StudentRepository
) {
    operator fun invoke(studentId: Int): Flow<Resource<Student?>> = flow {

        emit(Resource.Loading())

        val response = runCatching {
            studentRepository.getStudentDetailsById(studentId)
        }

        response.onSuccess { response ->
            if (response.isSuccessful) {
                Log.d("response", response.body()?.data.toString())
                emit(Resource.Success(response.body()?.data?.toStudent()))
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
