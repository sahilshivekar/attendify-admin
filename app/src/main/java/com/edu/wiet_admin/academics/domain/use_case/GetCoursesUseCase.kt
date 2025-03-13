package com.edu.wiet_admin.academics.domain.use_case

import com.edu.wiet_admin.academics.domain.repository.CourseRepository
import com.edu.wiet_admin.common.data.remote.Resource
import com.edu.wiet_admin.common.data.remote.WietApiResponse
import com.edu.wiet_admin.common.data.remote.response_dto.Course
import com.edu.wiet_admin.common.domain.RemoteUtils
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

// CourseRepository Use Cases
class GetCoursesUseCase @Inject constructor(private val courseRepository: CourseRepository) {
    operator fun invoke(searchQuery: String?, branchId: String?, semesterNumber: Int?, schemeId: String?, page: Int, limit: Int): Flow<Resource<WietApiResponse<List<Course?>>>> {
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