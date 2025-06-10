package com.attendify_admin.home.academics.data


import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.data.remote.response_dto.Semester
import com.attendify_admin.home.academics.data.dto.request.AddSemesterRequest
import com.attendify_admin.home.academics.data.dto.request.UpdateSemesterRequest
import com.attendify_admin.home.academics.domain.repository.SemesterRepository
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
    ): Response<AttendifyApiResponse<List<Semester?>>> =
        semesterApi.getSemesters(semesterNumber, academicStartYear, academicEndYear, branchId, schemeId, page, limit)

    override suspend fun addSemester(requestBody: AddSemesterRequest): Response<AttendifyApiResponse<Semester?>> =
        semesterApi.addSemester(requestBody)

    override suspend fun updateSemester(requestBody: UpdateSemesterRequest): Response<AttendifyApiResponse<Semester?>> =
        semesterApi.updateSemester(requestBody)

    override suspend fun removeSemester(semesterId: Int): Response<AttendifyApiResponse<String?>> =
        semesterApi.removeSemester(semesterId)

    override suspend fun getCoursesOfSemester(semesterId: Int): Response<AttendifyApiResponse<List<Semester?>>> =
        semesterApi.getCoursesOfSemester(semesterId)

    override suspend fun getSemesterById(semesterId: Int): Response<AttendifyApiResponse<Semester?>> =
        semesterApi.getSemesterById(semesterId)
}