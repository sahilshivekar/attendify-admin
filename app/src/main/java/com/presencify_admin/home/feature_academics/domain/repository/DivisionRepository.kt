package com.presencify_admin.home.feature_academics.domain.repository


import androidx.paging.PagingData
import com.presencify_admin.common.data.remote.dto.response.PresencifyApiResponse
import com.presencify_admin.common.data.remote.dto.response.DivisionDto
import com.presencify_admin.common.data.remote.dto.response.DivisionListWithTotalCountDto
import com.presencify_admin.home.feature_academics.data.remote.dto.request.AddDivisionRequest
import com.presencify_admin.home.feature_academics.data.remote.dto.request.UpdateDivisionRequest
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

   suspend fun getAllDivisions(
        semesterNumber: Int?,
        branchId: Int?,
        academicStartYear: Int?,
        academicEndYear: Int?,
        searchQuery: String?
    ): Response<PresencifyApiResponse<DivisionListWithTotalCountDto>>

    suspend fun addDivision(requestBody: AddDivisionRequest): Response<PresencifyApiResponse<DivisionDto?>>

    suspend fun updateDivision(requestBody: UpdateDivisionRequest): Response<PresencifyApiResponse<DivisionDto?>>

    suspend fun removeDivision(divisionId: Int): Response<PresencifyApiResponse<String?>>

    suspend fun getDivisionById(divisionId: Int): Response<PresencifyApiResponse<DivisionDto?>>
}