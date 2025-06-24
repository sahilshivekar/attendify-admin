package com.attendify_admin.home.feature_academics.domain.use_case

import androidx.paging.PagingData
import androidx.paging.map
import com.attendify_admin.common.data.remote.dto.response.toSemester
import com.attendify_admin.common.domain.model.Semester
import com.attendify_admin.home.feature_academics.domain.repository.SemesterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetSemestersUseCase @Inject constructor(
    private val semesterRepository: SemesterRepository,
) {
    operator fun invoke(
        semesterNumber: Int? = null,
        academicStartYear: Int? = null,
        academicEndYear: Int? = null,
        branchId: Int? = null,
        schemeId: Int? = null,
    ): Flow<PagingData<Semester>> {
        return semesterRepository.getSemesters(
            semesterNumber,
            academicStartYear,
            academicEndYear,
            branchId,
            schemeId
        ).map { pagingData ->
            pagingData.map { it.toSemester() }
        }
    }
}
