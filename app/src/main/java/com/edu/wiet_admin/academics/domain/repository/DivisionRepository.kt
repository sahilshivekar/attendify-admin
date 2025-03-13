package com.edu.wiet_admin.academics.domain.repository


import com.edu.wiet_admin.academics.data.dto.request.AddDivisionRequest
import com.edu.wiet_admin.academics.data.dto.request.UpdateDivisionRequest
import com.edu.wiet_admin.common.data.remote.WietApiResponse
import com.edu.wiet_admin.common.data.remote.response_dto.Division
import retrofit2.Response

interface DivisionRepository {
    suspend fun getDivisions(
        semesterNumber: Int?,
        branchId: String?,
        academicStartYear: Int?,
        academicEndYear: Int?,
        searchQuery: String?,
        page: Int,
        limit: Int
    ): Response<WietApiResponse<List<Division?>>>

    suspend fun addDivision(requestBody: AddDivisionRequest): Response<WietApiResponse<Division?>>

    suspend fun updateDivision(requestBody: UpdateDivisionRequest): Response<WietApiResponse<Division?>>

    suspend fun removeDivision(divisionId: String): Response<WietApiResponse<String?>>

    suspend fun getDivisionById(divisionId: String): Response<WietApiResponse<Division?>>
}