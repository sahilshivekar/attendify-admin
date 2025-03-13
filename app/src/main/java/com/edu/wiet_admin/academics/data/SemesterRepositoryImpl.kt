package com.edu.wiet_admin.academics.data


import com.edu.wiet_admin.academics.data.dto.request.AddSemesterRequest
import com.edu.wiet_admin.academics.data.dto.request.UpdateSemesterRequest
import com.edu.wiet_admin.academics.domain.repository.SemesterRepository
import com.edu.wiet_admin.common.data.remote.WietApiResponse
import com.edu.wiet_admin.common.data.remote.response_dto.Semester
import retrofit2.Response
import javax.inject.Inject

class SemesterRepositoryImpl @Inject constructor(
    private val semesterApi: SemesterApi
) : SemesterRepository {

    override suspend fun getSemesters(
        semesterNumber: Int?,
        academicStartYear: Int?,
        academicEndYear: Int?,
        branchId: String?,
        schemeId: String?,
        page: Int,
        limit: Int
    ): Response<WietApiResponse<List<Semester?>>> =
        semesterApi.getSemesters(semesterNumber, academicStartYear, academicEndYear, branchId, schemeId, page, limit)

    override suspend fun addSemester(requestBody: AddSemesterRequest): Response<WietApiResponse<Semester?>> =
        semesterApi.addSemester(requestBody)

    override suspend fun updateSemester(requestBody: UpdateSemesterRequest): Response<WietApiResponse<Semester?>> =
        semesterApi.updateSemester(requestBody)

    override suspend fun removeSemester(semesterId: String): Response<WietApiResponse<String?>> =
        semesterApi.removeSemester(semesterId)

    override suspend fun getCoursesOfSemester(semesterId: String): Response<WietApiResponse<List<Semester?>>> =
        semesterApi.getCoursesOfSemester(semesterId)

    override suspend fun getSemesterById(semesterId: String): Response<WietApiResponse<Semester?>> =
        semesterApi.getSemesterById(semesterId)
}