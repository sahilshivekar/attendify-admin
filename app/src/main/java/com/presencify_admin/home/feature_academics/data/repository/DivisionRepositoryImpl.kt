package com.presencify_admin.home.feature_academics.data.repository


import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.presencify_admin.common.data.remote.dto.response.PresencifyApiResponse
import com.presencify_admin.common.data.remote.dto.response.DivisionDto
import com.presencify_admin.common.data.remote.dto.response.DivisionListWithTotalCountDto
import com.presencify_admin.home.feature_academics.data.remote.DivisionApi
import com.presencify_admin.home.feature_academics.data.remote.dto.request.AddDivisionRequest
import com.presencify_admin.home.feature_academics.data.remote.dto.request.UpdateDivisionRequest
import com.presencify_admin.home.feature_academics.domain.repository.DivisionRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class DivisionRepositoryImpl @Inject constructor(
    private val divisionApi: DivisionApi,
) : DivisionRepository {

    override fun getDivisions(
        semesterNumber: Int?,
        branchId: Int?,
        academicStartYear: Int?,
        academicEndYear: Int?,
        searchQuery: String?,
    ): Flow<PagingData<DivisionDto>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                GetDivisionsPagingSource(
                    divisionApi = divisionApi,
                    semesterNumber = semesterNumber,
                    branchId = branchId,
                    academicStartYear = academicStartYear,
                    academicEndYear = academicEndYear,
                    searchQuery = searchQuery
                )
            }
        ).flow
    }

    override suspend fun getAllDivisions(
        semesterNumber: Int?,
        branchId: Int?,
        academicStartYear: Int?,
        academicEndYear: Int?,
        searchQuery: String?,
    ): Response<PresencifyApiResponse<DivisionListWithTotalCountDto>> = divisionApi.getDivisions(
        semesterNumber = semesterNumber,
        branchId = branchId,
        academicStartYear = academicStartYear,
        academicEndYear = academicEndYear,
        searchQuery = searchQuery,
        getAll = true
    )


    override suspend fun addDivision(requestBody: AddDivisionRequest): Response<PresencifyApiResponse<DivisionDto?>> =
        divisionApi.addDivision(requestBody)

    override suspend fun updateDivision(requestBody: UpdateDivisionRequest): Response<PresencifyApiResponse<DivisionDto?>> =
        divisionApi.updateDivision(requestBody)

    override suspend fun removeDivision(divisionId: Int): Response<PresencifyApiResponse<String?>> =
        divisionApi.removeDivision(divisionId)

    override suspend fun getDivisionById(divisionId: Int): Response<PresencifyApiResponse<DivisionDto?>> =
        divisionApi.getDivisionById(divisionId)
}