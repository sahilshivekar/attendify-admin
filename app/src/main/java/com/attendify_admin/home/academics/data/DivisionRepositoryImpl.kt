package com.attendify_admin.home.academics.data


import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.data.remote.response_dto.Division
import com.attendify_admin.home.academics.data.dto.request.AddDivisionRequest
import com.attendify_admin.home.academics.data.dto.request.UpdateDivisionRequest
import com.attendify_admin.home.academics.domain.repository.DivisionRepository
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
    ): Response<AttendifyApiResponse<List<Division?>>> =
        divisionApi.getDivisions(semesterNumber, branchId, academicStartYear, academicEndYear, searchQuery, page, limit)

    override suspend fun addDivision(requestBody: AddDivisionRequest): Response<AttendifyApiResponse<Division?>> =
        divisionApi.addDivision(requestBody)

    override suspend fun updateDivision(requestBody: UpdateDivisionRequest): Response<AttendifyApiResponse<Division?>> =
        divisionApi.updateDivision(requestBody)

    override suspend fun removeDivision(divisionId: Int): Response<AttendifyApiResponse<String?>> =
        divisionApi.removeDivision(divisionId)

    override suspend fun getDivisionById(divisionId: Int): Response<AttendifyApiResponse<Division?>> =
        divisionApi.getDivisionById(divisionId)
}