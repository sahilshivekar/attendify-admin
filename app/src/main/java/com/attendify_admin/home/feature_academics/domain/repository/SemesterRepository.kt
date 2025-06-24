package com.attendify_admin.home.feature_academics.domain.repository


import androidx.paging.PagingData
import com.attendify_admin.common.data.remote.dto.response.AttendifyApiResponse
import com.attendify_admin.common.data.remote.dto.response.SemesterDto
import com.attendify_admin.common.data.remote.dto.response.SemesterListWithTotalCountDto
import com.attendify_admin.home.feature_academics.data.remote.dto.request.AddSemesterRequest
import com.attendify_admin.home.feature_academics.data.remote.dto.request.UpdateSemesterRequest
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
    ): Response<AttendifyApiResponse<SemesterListWithTotalCountDto>>


    suspend fun addSemester(requestBody: AddSemesterRequest): Response<AttendifyApiResponse<SemesterDto?>>

    suspend fun updateSemester(requestBody: UpdateSemesterRequest): Response<AttendifyApiResponse<SemesterDto?>>

    suspend fun removeSemester(semesterId: Int): Response<AttendifyApiResponse<String?>>

    suspend fun getCoursesOfSemester(semesterId: Int): Response<AttendifyApiResponse<List<SemesterDto>?>>

    suspend fun getSemesterById(semesterId: Int): Response<AttendifyApiResponse<SemesterDto?>>
}