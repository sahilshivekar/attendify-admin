package com.attendify_admin.home.feature_academics.data


import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.data.remote.dto.response.SemesterDto
import com.attendify_admin.home.feature_academics.data.dto.request.AddSemesterRequest
import com.attendify_admin.home.feature_academics.data.dto.request.UpdateSemesterRequest
import com.attendify_admin.home.feature_academics.domain.repository.SemesterRepository
import retrofit2.Response
import javax.inject.Inject

class SemesterRepositoryImpl @Inject constructor(
    private val semesterApi: SemesterApi
) : SemesterRepository {

    override suspend fun getSemesters(
        semesterNumber: Int?,
        academicStartYear: Int?,
        academicEndYear: Int?,
        branchId: Int?,
        schemeId: Int?,
        page: Int,
        limit: Int
    ): Response<AttendifyApiResponse<List<SemesterDto>?>> =
        semesterApi.getSemesters(semesterNumber, academicStartYear, academicEndYear, branchId, schemeId, page, limit)

    override suspend fun addSemester(requestBody: AddSemesterRequest): Response<AttendifyApiResponse<SemesterDto?>> =
        semesterApi.addSemester(requestBody)

    override suspend fun updateSemester(requestBody: UpdateSemesterRequest): Response<AttendifyApiResponse<SemesterDto?>> =
        semesterApi.updateSemester(requestBody)

    override suspend fun removeSemester(semesterId: Int): Response<AttendifyApiResponse<String?>> =
        semesterApi.removeSemester(semesterId)

    override suspend fun getCoursesOfSemester(semesterId: Int): Response<AttendifyApiResponse<List<SemesterDto>?>> =
        semesterApi.getCoursesOfSemester(semesterId)

    override suspend fun getSemesterById(semesterId: Int): Response<AttendifyApiResponse<SemesterDto?>> =
        semesterApi.getSemesterById(semesterId)
}