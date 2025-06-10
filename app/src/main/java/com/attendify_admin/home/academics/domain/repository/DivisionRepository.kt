package com.attendify_admin.home.academics.domain.repository


import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.data.remote.response_dto.Division
import com.attendify_admin.home.academics.data.dto.request.AddDivisionRequest
import com.attendify_admin.home.academics.data.dto.request.UpdateDivisionRequest
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
    ): Response<AttendifyApiResponse<List<Division?>>>

    suspend fun addDivision(requestBody: AddDivisionRequest): Response<AttendifyApiResponse<Division?>>

    suspend fun updateDivision(requestBody: UpdateDivisionRequest): Response<AttendifyApiResponse<Division?>>

    suspend fun removeDivision(divisionId: Int): Response<AttendifyApiResponse<String?>>

    suspend fun getDivisionById(divisionId: Int): Response<AttendifyApiResponse<Division?>>
}