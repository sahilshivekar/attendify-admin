package com.attendify_admin.home.feature_academics.domain.repository


import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.data.remote.dto.response.DivisionDto
import com.attendify_admin.home.feature_academics.data.dto.request.AddDivisionRequest
import com.attendify_admin.home.feature_academics.data.dto.request.UpdateDivisionRequest
import retrofit2.Response

interface DivisionRepository {
    suspend fun getDivisions(
        semesterNumber: Int?,
        branchId: Int?,
        academicStartYear: Int?,
        academicEndYear: Int?,
        searchQuery: String?,
        page: Int,
        limit: Int
    ): Response<AttendifyApiResponse<List<DivisionDto>?>>

    suspend fun addDivision(requestBody: AddDivisionRequest): Response<AttendifyApiResponse<DivisionDto?>>

    suspend fun updateDivision(requestBody: UpdateDivisionRequest): Response<AttendifyApiResponse<DivisionDto?>>

    suspend fun removeDivision(divisionId: Int): Response<AttendifyApiResponse<String?>>

    suspend fun getDivisionById(divisionId: Int): Response<AttendifyApiResponse<DivisionDto?>>
}