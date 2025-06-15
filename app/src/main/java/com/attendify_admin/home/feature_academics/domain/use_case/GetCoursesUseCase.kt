package com.attendify_admin.home.feature_academics.domain.use_case

import com.attendify_admin.common.data.remote.Resource
import com.attendify_admin.common.data.remote.dto.response.toCourse
import com.attendify_admin.common.domain.RemoteUtils
import com.attendify_admin.common.domain.model.Course
import com.attendify_admin.home.feature_academics.domain.repository.CourseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetCoursesUseCase @Inject constructor(
    private val courseRepository: CourseRepository,
) {
    operator fun invoke(
        searchQuery: String?,
        branchId: Int?,
        semesterNumber: Int?,
        schemeId: Int?,
        page: Int,
        limit: Int,
    ): Flow<Resource<List<Course>?>> = flow {

        emit(Resource.Loading())

        val response = runCatching {
            courseRepository.getCourses(
                searchQuery,
                branchId,
                semesterNumber,
                schemeId,
                page,
                limit
            )
        }

        response.onSuccess { response ->
            if (response.isSuccessful) {
                emit(Resource.Success(response.body()?.data?.map { it.toCourse() }))
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
