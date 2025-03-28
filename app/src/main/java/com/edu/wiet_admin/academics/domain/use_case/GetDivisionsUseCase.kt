package com.edu.wiet_admin.academics.domain.use_case

import com.edu.wiet_admin.academics.domain.repository.DivisionRepository
import com.edu.wiet_admin.common.data.remote.Resource
import com.edu.wiet_admin.common.data.remote.WietApiResponse
import com.edu.wiet_admin.common.data.remote.response_dto.Division
import com.edu.wiet_admin.common.domain.RemoteUtils
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
    ): Flow<Resource<WietApiResponse<List<Division?>>>> {
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