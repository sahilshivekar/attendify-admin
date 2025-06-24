package com.attendify_admin.home.feature_academics.domain.use_case

import androidx.paging.PagingData
import androidx.paging.map
import com.attendify_admin.common.data.remote.dto.response.toCourse
import com.attendify_admin.common.domain.model.Course
import com.attendify_admin.home.feature_academics.domain.repository.CourseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetCoursesUseCase @Inject constructor(
    private val courseRepository: CourseRepository
) {
    operator fun invoke(
        searchQuery: String?,
        branchId: Int?,
        semesterNumber: Int?,
        schemeId: Int?
    ): Flow<PagingData<Course>> {
        return courseRepository.getCourses(
            searchQuery,
            branchId,
            semesterNumber,
            schemeId
        ).map { pagingData ->
            pagingData.map { it.toCourse() }
        }
    }
}

