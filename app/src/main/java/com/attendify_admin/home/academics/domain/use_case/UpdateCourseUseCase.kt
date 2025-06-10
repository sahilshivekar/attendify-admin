package com.attendify_admin.home.academics.domain.use_case

import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.data.remote.Resource
import com.attendify_admin.common.data.dto.response.Course
import com.attendify_admin.common.domain.RemoteUtils
import com.attendify_admin.home.academics.data.dto.request.UpdateCourseRequest
import com.attendify_admin.home.academics.domain.repository.CourseRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpdateCourseUseCase @Inject constructor(private val courseRepository: CourseRepository) {
    operator fun invoke(requestBody: UpdateCourseRequest): Flow<Resource<AttendifyApiResponse<Course?>>> {
        return RemoteUtils.responseFlow { courseRepository.updateCourse(requestBody) }
    }
}