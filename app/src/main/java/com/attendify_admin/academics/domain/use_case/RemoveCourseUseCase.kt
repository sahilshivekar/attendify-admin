package com.attendify_admin.academics.domain.use_case

import com.attendify_admin.academics.domain.repository.CourseRepository
import com.attendify_admin.common.data.remote.Resource
import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.domain.RemoteUtils
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RemoveCourseUseCase @Inject constructor(private val courseRepository: CourseRepository) {
    operator fun invoke(courseId: String): Flow<Resource<AttendifyApiResponse<String?>>> {
        return RemoteUtils.responseFlow { courseRepository.removeCourse(courseId) }
    }
}