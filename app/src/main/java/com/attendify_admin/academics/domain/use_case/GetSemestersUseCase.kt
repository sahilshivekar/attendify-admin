package com.attendify_admin.academics.domain.use_case

import com.attendify_admin.academics.domain.repository.SemesterRepository
import com.attendify_admin.common.data.remote.Resource
import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.data.remote.response_dto.Semester
import com.attendify_admin.common.domain.RemoteUtils
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

// SemesterRepository Use Cases
class GetSemestersUseCase @Inject constructor(private val semesterRepository: SemesterRepository) {
    operator fun invoke(
        semesterNumber: Int?,
        academicStartYear: Int?,
        academicEndYear: Int?,
        branchId: String?,
        schemeId: String?,
        page: Int,
        limit: Int
    ): Flow<Resource<AttendifyApiResponse<List<Semester?>>>> {
        return RemoteUtils.responseFlow {
            semesterRepository.getSemesters(
                semesterNumber,
                academicStartYear,
                academicEndYear,
                branchId,
                schemeId,
                page,
                limit
            )
        }
    }
}