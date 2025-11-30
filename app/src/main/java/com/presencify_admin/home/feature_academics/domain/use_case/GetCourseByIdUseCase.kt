package com.presencify_admin.home.feature_academics.domain.use_case

import com.presencify_admin.common.data.remote.Resource
import com.presencify_admin.common.data.remote.dto.response.toCourse
import com.presencify_admin.common.domain.RemoteUtils
import com.presencify_admin.common.domain.model.Course
import com.presencify_admin.home.feature_academics.domain.repository.CourseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetCourseByIdUseCase @Inject constructor(
    private val courseRepository: CourseRepository
) {
    operator fun invoke(courseId: Int): Flow<Resource<Course>> = flow {

        emit(Resource.Loading<Course>())

        val response = runCatching {
            courseRepository.getCourseById(courseId)
        }

        response.onSuccess { response ->
            if (response.isSuccessful) {
                emit(Resource.Success(response.body()?.data?.toCourse()))
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
