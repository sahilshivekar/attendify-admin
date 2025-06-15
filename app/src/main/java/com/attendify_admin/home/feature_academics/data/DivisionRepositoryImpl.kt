package com.attendify_admin.home.feature_academics.data


import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.data.remote.dto.response.DivisionDto
import com.attendify_admin.home.feature_academics.data.dto.request.AddDivisionRequest
import com.attendify_admin.home.feature_academics.data.dto.request.UpdateDivisionRequest
import com.attendify_admin.home.feature_academics.domain.repository.DivisionRepository
import retrofit2.Response
import javax.inject.Inject

class DivisionRepositoryImpl @Inject constructor(
    private val divisionApi: DivisionApi
) : DivisionRepository {

    override suspend fun getDivisions(
        semesterNumber: Int?,
        branchId: Int?,
        academicStartYear: Int?,
        academicEndYear: Int?,
        searchQuery: String?,
        page: Int,
        limit: Int
    ): Response<AttendifyApiResponse<List<DivisionDto>?>> =
        divisionApi.getDivisions(semesterNumber, branchId, academicStartYear, academicEndYear, searchQuery, page, limit)

    override suspend fun addDivision(requestBody: AddDivisionRequest): Response<AttendifyApiResponse<DivisionDto?>> =
        divisionApi.addDivision(requestBody)

    override suspend fun updateDivision(requestBody: UpdateDivisionRequest): Response<AttendifyApiResponse<DivisionDto?>> =
        divisionApi.updateDivision(requestBody)

    override suspend fun removeDivision(divisionId: Int): Response<AttendifyApiResponse<String?>> =
        divisionApi.removeDivision(divisionId)

    override suspend fun getDivisionById(divisionId: Int): Response<AttendifyApiResponse<DivisionDto?>> =
        divisionApi.getDivisionById(divisionId)
}