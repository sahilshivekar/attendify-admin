package com.edu.wiet_admin.academics.domain.use_case

import com.edu.wiet_admin.academics.data.dto.request.RemoveCourseFromBranchWithSemesterNumberRequest
import com.edu.wiet_admin.academics.domain.repository.CourseRepository
import com.edu.wiet_admin.common.data.remote.Resource
import com.edu.wiet_admin.common.data.remote.WietApiResponse
import com.edu.wiet_admin.common.domain.RemoteUtils
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RemoveCourseFromBranchWithSemesterNumberUseCase @Inject constructor(private val courseRepository: CourseRepository) {
    operator fun invoke(requestBody: RemoveCourseFromBranchWithSemesterNumberRequest): Flow<Resource<WietApiResponse<String?>>> {
        return RemoteUtils.responseFlow {
            courseRepository.removeCourseFromBranchWithSemesterNumber(
                requestBody
            )
        }
    }
}