package com.attendify_admin.home.academics.domain.use_case

import com.attendify_admin.home.academics.domain.repository.DivisionRepository
import com.attendify_admin.common.data.remote.Resource
import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.data.remote.response_dto.Division
import com.attendify_admin.common.domain.RemoteUtils
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

// DivisionRepository Use Cases
class GetDivisionsUseCase @Inject constructor(private val divisionRepository: DivisionRepository) {
    operator fun invoke(
        semesterNumber: Int? = null,
        branchId: String? = null,
        academicStartYear: Int? = null,
        academicEndYear: Int? = null,
        searchQuery: String? = null,
        page: Int,
        limit: Int
    ): Flow<Resource<AttendifyApiResponse<List<Division?>>>> {
        return RemoteUtils.responseFlow {
            divisionRepository.getDivisions(
                semesterNumber,
                branchId,
                academicStartYear,
                academicEndYear,
                searchQuery,
                page,
                limit
            )
        }
    }
}