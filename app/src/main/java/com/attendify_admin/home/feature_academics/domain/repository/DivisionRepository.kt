package com.attendify_admin.home.feature_academics.domain.repository


import androidx.paging.PagingData
import com.attendify_admin.common.data.remote.dto.response.AttendifyApiResponse
import com.attendify_admin.common.data.remote.dto.response.DivisionDto
import com.attendify_admin.home.feature_academics.data.remote.dto.request.AddDivisionRequest
import com.attendify_admin.home.feature_academics.data.remote.dto.request.UpdateDivisionRequest
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface DivisionRepository {
    fun getDivisions(
        semesterNumber: Int?,
        branchId: Int?,
        academicStartYear: Int?,
        academicEndYear: Int?,
        searchQuery: String?
    ): Flow<PagingData<DivisionDto>>

    suspend fun addDivision(requestBody: AddDivisionRequest): Response<AttendifyApiResponse<DivisionDto?>>

    suspend fun updateDivision(requestBody: UpdateDivisionRequest): Response<AttendifyApiResponse<DivisionDto?>>

    suspend fun removeDivision(divisionId: Int): Response<AttendifyApiResponse<String?>>

    suspend fun getDivisionById(divisionId: Int): Response<AttendifyApiResponse<DivisionDto?>>
}