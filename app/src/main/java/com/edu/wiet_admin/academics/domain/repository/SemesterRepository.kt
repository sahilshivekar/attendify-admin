package com.edu.wiet_admin.academics.domain.repository


import com.edu.wiet_admin.academics.data.dto.request.AddSemesterRequest
import com.edu.wiet_admin.academics.data.dto.request.UpdateSemesterRequest
import com.edu.wiet_admin.common.data.remote.WietApiResponse
import com.edu.wiet_admin.common.data.remote.response_dto.Semester
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
    ): Response<WietApiResponse<List<Semester?>>>

    suspend fun addSemester(requestBody: AddSemesterRequest): Response<WietApiResponse<Semester?>>

    suspend fun updateSemester(requestBody: UpdateSemesterRequest): Response<WietApiResponse<Semester?>>

    suspend fun removeSemester(semesterId: String): Response<WietApiResponse<String?>>

    suspend fun getCoursesOfSemester(semesterId: String): Response<WietApiResponse<List<Semester?>>>

    suspend fun getSemesterById(semesterId: String): Response<WietApiResponse<Semester?>>
}