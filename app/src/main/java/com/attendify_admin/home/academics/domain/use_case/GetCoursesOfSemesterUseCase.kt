package com.attendify_admin.home.academics.domain.use_case

import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.data.remote.Resource
import com.attendify_admin.common.data.remote.response_dto.Semester
import com.attendify_admin.common.domain.RemoteUtils
import com.attendify_admin.home.academics.domain.repository.SemesterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCoursesOfSemesterUseCase @Inject constructor(private val semesterRepository: SemesterRepository) {
    operator fun invoke(semesterId: Int): Flow<Resource<AttendifyApiResponse<List<Semester?>>>> {
        return RemoteUtils.responseFlow { semesterRepository.getCoursesOfSemester(semesterId) }
    }
}