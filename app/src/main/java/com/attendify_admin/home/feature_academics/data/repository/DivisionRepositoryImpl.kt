package com.attendify_admin.home.feature_academics.data.repository


import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.attendify_admin.common.data.remote.dto.response.AttendifyApiResponse
import com.attendify_admin.common.data.remote.dto.response.DivisionDto
import com.attendify_admin.common.data.remote.dto.response.DivisionListWithTotalCountDto
import com.attendify_admin.home.feature_academics.data.remote.DivisionApi
import com.attendify_admin.home.feature_academics.data.remote.dto.request.AddDivisionRequest
import com.attendify_admin.home.feature_academics.data.remote.dto.request.UpdateDivisionRequest
import com.attendify_admin.home.feature_academics.domain.repository.DivisionRepository
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
    ): Response<AttendifyApiResponse<DivisionListWithTotalCountDto>> = divisionApi.getDivisions(
        semesterNumber = semesterNumber,
        branchId = branchId,
        academicStartYear = academicStartYear,
        academicEndYear = academicEndYear,
        searchQuery = searchQuery,
        getAll = true
    )


    override suspend fun addDivision(requestBody: AddDivisionRequest): Response<AttendifyApiResponse<DivisionDto?>> =
        divisionApi.addDivision(requestBody)

    override suspend fun updateDivision(requestBody: UpdateDivisionRequest): Response<AttendifyApiResponse<DivisionDto?>> =
        divisionApi.updateDivision(requestBody)

    override suspend fun removeDivision(divisionId: Int): Response<AttendifyApiResponse<String?>> =
        divisionApi.removeDivision(divisionId)

    override suspend fun getDivisionById(divisionId: Int): Response<AttendifyApiResponse<DivisionDto?>> =
        divisionApi.getDivisionById(divisionId)
}