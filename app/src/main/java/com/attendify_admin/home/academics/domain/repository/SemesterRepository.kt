package com.attendify_admin.home.academics.domain.repository


import com.attendify_admin.home.academics.data.dto.request.AddSemesterRequest
import com.attendify_admin.home.academics.data.dto.request.UpdateSemesterRequest
import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.data.remote.response_dto.Semester
import retrofit2.Response

interface SemesterRepository {
    suspend fun getSemesters(
        semesterNumber: Int?,
        academicStartYear: Int?,
        academicEndYear: Int?,
        branchId: String?,
        schemeId: String?,
        page: Int,
        limit: Int
    ): Response<AttendifyApiResponse<List<Semester?>>>

    suspend fun addSemester(requestBody: AddSemesterRequest): Response<AttendifyApiResponse<Semester?>>

    suspend fun updateSemester(requestBody: UpdateSemesterRequest): Response<AttendifyApiResponse<Semester?>>

    suspend fun removeSemester(semesterId: String): Response<AttendifyApiResponse<String?>>

    suspend fun getCoursesOfSemester(semesterId: String): Response<AttendifyApiResponse<List<Semester?>>>

    suspend fun getSemesterById(semesterId: String): Response<AttendifyApiResponse<Semester?>>
}