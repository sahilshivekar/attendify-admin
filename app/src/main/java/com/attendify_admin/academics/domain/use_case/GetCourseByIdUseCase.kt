package com.attendify_admin.academics.domain.use_case

import com.attendify_admin.academics.domain.repository.CourseRepository
import com.attendify_admin.common.data.remote.Resource
import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.data.remote.response_dto.Course
import com.attendify_admin.common.domain.RemoteUtils
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCourseByIdUseCase @Inject constructor(private val courseRepository: CourseRepository) {
    operator fun invoke(courseId: String): Flow<Resource<AttendifyApiResponse<Course?>>> {
        return RemoteUtils.responseFlow { courseRepository.getCourseById(courseId) }
    }
}