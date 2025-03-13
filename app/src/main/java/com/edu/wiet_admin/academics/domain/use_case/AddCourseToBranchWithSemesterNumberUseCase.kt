package com.edu.wiet_admin.academics.domain.use_case

import com.edu.wiet_admin.academics.data.dto.request.AddCourseToBranchWithSemesterNumberRequest
import com.edu.wiet_admin.academics.domain.repository.CourseRepository
import com.edu.wiet_admin.common.data.remote.Resource
import com.edu.wiet_admin.common.data.remote.WietApiResponse
import com.edu.wiet_admin.common.data.remote.response_dto.Course
import com.edu.wiet_admin.common.domain.RemoteUtils
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AddCourseToBranchWithSemesterNumberUseCase @Inject constructor(private val courseRepository: CourseRepository) {
    operator fun invoke(requestBody: AddCourseToBranchWithSemesterNumberRequest): Flow<Resource<WietApiResponse<Course?>>> {
        return RemoteUtils.responseFlow {
            courseRepository.addCourseToBranchWithSemesterNumber(
                requestBody
            )
        }
    }
}