package com.presencify_admin.home.feature_academics.domain.repository


import androidx.paging.PagingData
import com.presencify_admin.common.data.remote.dto.response.PresencifyApiResponse
import com.presencify_admin.common.data.remote.dto.response.SemesterDto
import com.presencify_admin.common.data.remote.dto.response.SemesterListWithTotalCountDto
import com.presencify_admin.home.feature_academics.data.remote.dto.request.AddSemesterRequest
import com.presencify_admin.home.feature_academics.data.remote.dto.request.UpdateSemesterRequest
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface SemesterRepository {
    fun getSemesters(
        semesterNumber: Int?,
        academicStartYear: Int?,
        academicEndYear: Int?,
        branchId: Int?,
        schemeId: Int?
    ): Flow<PagingData<SemesterDto>>

    suspend fun getAllSemesters(
        semesterNumber: Int?,
        academicStartYear: Int?,
        academicEndYear: Int?,
        branchId: Int?,
        schemeId: Int?
    ): Response<PresencifyApiResponse<SemesterListWithTotalCountDto>>


    suspend fun addSemester(requestBody: AddSemesterRequest): Response<PresencifyApiResponse<SemesterDto?>>

    suspend fun updateSemester(requestBody: UpdateSemesterRequest): Response<PresencifyApiResponse<SemesterDto?>>

    suspend fun removeSemester(semesterId: Int): Response<PresencifyApiResponse<String?>>

    suspend fun getCoursesOfSemester(semesterId: Int): Response<PresencifyApiResponse<List<SemesterDto>?>>

    suspend fun getSemesterById(semesterId: Int): Response<PresencifyApiResponse<SemesterDto?>>
}