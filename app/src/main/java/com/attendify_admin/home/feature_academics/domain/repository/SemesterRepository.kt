package com.attendify_admin.home.feature_academics.domain.repository


import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.data.remote.dto.response.SemesterDto
import com.attendify_admin.home.feature_academics.data.dto.request.AddSemesterRequest
import com.attendify_admin.home.feature_academics.data.dto.request.UpdateSemesterRequest
import retrofit2.Response

interface SemesterRepository {
    suspend fun getSemesters(
        semesterNumber: Int?,
        academicStartYear: Int?,
        academicEndYear: Int?,
        branchId: Int?,
        schemeId: Int?,
        page: Int,
        limit: Int
    ): Response<AttendifyApiResponse<List<SemesterDto>?>>

    suspend fun addSemester(requestBody: AddSemesterRequest): Response<AttendifyApiResponse<SemesterDto?>>

    suspend fun updateSemester(requestBody: UpdateSemesterRequest): Response<AttendifyApiResponse<SemesterDto?>>

    suspend fun removeSemester(semesterId: Int): Response<AttendifyApiResponse<String?>>

    suspend fun getCoursesOfSemester(semesterId: Int): Response<AttendifyApiResponse<List<SemesterDto>?>>

    suspend fun getSemesterById(semesterId: Int): Response<AttendifyApiResponse<SemesterDto?>>
}