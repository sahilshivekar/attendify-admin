package com.edu.wiet_admin.academics.domain.use_case

import com.edu.wiet_admin.academics.domain.repository.SemesterRepository
import com.edu.wiet_admin.common.data.remote.Resource
import com.edu.wiet_admin.common.data.remote.WietApiResponse
import com.edu.wiet_admin.common.data.remote.response_dto.Semester
import com.edu.wiet_admin.common.domain.RemoteUtils
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCoursesOfSemesterUseCase @Inject constructor(private val semesterRepository: SemesterRepository) {
    operator fun invoke(semesterId: String): Flow<Resource<WietApiResponse<List<Semester?>>>> {
        return RemoteUtils.responseFlow { semesterRepository.getCoursesOfSemester(semesterId) }
    }
}