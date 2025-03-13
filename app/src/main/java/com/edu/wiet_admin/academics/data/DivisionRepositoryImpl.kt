package com.edu.wiet_admin.academics.data


import com.edu.wiet_admin.academics.data.dto.request.AddDivisionRequest
import com.edu.wiet_admin.academics.data.dto.request.UpdateDivisionRequest
import com.edu.wiet_admin.academics.domain.repository.DivisionRepository
import com.edu.wiet_admin.common.data.remote.WietApiResponse
import com.edu.wiet_admin.common.data.remote.response_dto.Division
import retrofit2.Response
import javax.inject.Inject

class DivisionRepositoryImpl @Inject constructor(
    private val divisionApi: DivisionApi
) : DivisionRepository {

    override suspend fun getDivisions(
        semesterNumber: Int?,
        branchId: String?,
        academicStartYear: Int?,
        academicEndYear: Int?,
        searchQuery: String?,
        page: Int,
        limit: Int
    ): Response<WietApiResponse<List<Division?>>> =
        divisionApi.getDivisions(semesterNumber, branchId, academicStartYear, academicEndYear, searchQuery, page, limit)

    override suspend fun addDivision(requestBody: AddDivisionRequest): Response<WietApiResponse<Division?>> =
        divisionApi.addDivision(requestBody)

    override suspend fun updateDivision(requestBody: UpdateDivisionRequest): Response<WietApiResponse<Division?>> =
        divisionApi.updateDivision(requestBody)

    override suspend fun removeDivision(divisionId: String): Response<WietApiResponse<String?>> =
        divisionApi.removeDivision(divisionId)

    override suspend fun getDivisionById(divisionId: String): Response<WietApiResponse<Division?>> =
        divisionApi.getDivisionById(divisionId)
}