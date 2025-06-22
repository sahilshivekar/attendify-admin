package com.attendify_admin.home.feature_academics.domain.use_case

// DivisionRepository Use Cases
import androidx.paging.PagingData
import androidx.paging.map
import com.attendify_admin.common.data.remote.Resource
import com.attendify_admin.common.data.remote.dto.response.toDivision
import com.attendify_admin.common.domain.RemoteUtils
import com.attendify_admin.common.domain.model.Division
import com.attendify_admin.home.feature_academics.domain.repository.DivisionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject
class GetDivisionsUseCase @Inject constructor(
    private val divisionRepository: DivisionRepository
) {
    operator fun invoke(
        semesterNumber: Int? = null,
        branchId: Int? = null,
        academicStartYear: Int? = null,
        academicEndYear: Int? = null,
        searchQuery: String? = null,
    ): Flow<PagingData<Division>> {
        return divisionRepository.getDivisions(
            semesterNumber,
            branchId,
            academicStartYear,
            academicEndYear,
            searchQuery
        ).map { pagingData ->
            pagingData.map { it.toDivision() }
        }
    }
}
