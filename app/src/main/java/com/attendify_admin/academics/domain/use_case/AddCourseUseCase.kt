package com.attendify_admin.academics.domain.use_case

import com.attendify_admin.academics.data.dto.request.AddCourseRequest
import com.attendify_admin.academics.domain.repository.CourseRepository
import com.attendify_admin.common.data.remote.Resource
import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.data.remote.response_dto.Course
import com.attendify_admin.common.domain.RemoteUtils
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AddCourseUseCase @Inject constructor(private val courseRepository: CourseRepository) {
    operator fun invoke(requestBody: AddCourseRequest): Flow<Resource<AttendifyApiResponse<Course?>>> {
        return RemoteUtils.responseFlow { courseRepository.addCourse(requestBody) }
    }
}