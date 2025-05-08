package com.attendify_admin.home.academics.domain.use_case

import com.attendify_admin.home.academics.data.dto.request.AddCourseToBranchWithSemesterNumberRequest
import com.attendify_admin.home.academics.domain.repository.CourseRepository
import com.attendify_admin.common.data.remote.Resource
import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.data.remote.response_dto.Course
import com.attendify_admin.common.domain.RemoteUtils
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AddCourseToBranchWithSemesterNumberUseCase @Inject constructor(private val courseRepository: CourseRepository) {
    operator fun invoke(requestBody: AddCourseToBranchWithSemesterNumberRequest): Flow<Resource<AttendifyApiResponse<Course?>>> {
        return RemoteUtils.responseFlow {
            courseRepository.addCourseToBranchWithSemesterNumber(
                requestBody
            )
        }
    }
}