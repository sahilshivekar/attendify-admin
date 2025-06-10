package com.attendify_admin.home.academics.domain.use_case

import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.data.remote.Resource
import com.attendify_admin.common.data.dto.response.Course
import com.attendify_admin.common.domain.RemoteUtils
import com.attendify_admin.home.academics.domain.repository.CourseRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

// CourseRepository Use Cases
class GetCoursesUseCase @Inject constructor(private val courseRepository: CourseRepository) {
    operator fun invoke(searchQuery: String?, branchId: Int?, semesterNumber: Int?, schemeId: Int?, page: Int, limit: Int): Flow<Resource<AttendifyApiResponse<List<Course?>>>> {
        return RemoteUtils.responseFlow {
            courseRepository.getCourses(
                searchQuery,
                branchId,
                semesterNumber,
                schemeId,
                page,
                limit
            )
        }
    }
}