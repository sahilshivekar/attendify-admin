package com.attendify_admin.home.academics.domain.repository


import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.data.remote.response_dto.Semester
import com.attendify_admin.home.academics.data.dto.request.AddSemesterRequest
import com.attendify_admin.home.academics.data.dto.request.UpdateSemesterRequest
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
    ): Response<AttendifyApiResponse<List<Semester?>>>

    suspend fun addSemester(requestBody: AddSemesterRequest): Response<AttendifyApiResponse<Semester?>>

    suspend fun updateSemester(requestBody: UpdateSemesterRequest): Response<AttendifyApiResponse<Semester?>>

    suspend fun removeSemester(semesterId: Int): Response<AttendifyApiResponse<String?>>

    suspend fun getCoursesOfSemester(semesterId: Int): Response<AttendifyApiResponse<List<Semester?>>>

    suspend fun getSemesterById(semesterId: Int): Response<AttendifyApiResponse<Semester?>>
}