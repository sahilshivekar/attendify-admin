package com.attendify_admin.home.feature_academics.data.repository


import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.attendify_admin.common.data.remote.dto.response.AttendifyApiResponse
import com.attendify_admin.common.data.remote.dto.response.SemesterDto
import com.attendify_admin.home.feature_academics.data.remote.SemesterApi
import com.attendify_admin.home.feature_academics.data.remote.dto.request.AddSemesterRequest
import com.attendify_admin.home.feature_academics.data.remote.dto.request.UpdateSemesterRequest
import com.attendify_admin.home.feature_academics.domain.repository.SemesterRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class SemesterRepositoryImpl @Inject constructor(
    private val semesterApi: SemesterApi
) : SemesterRepository {

    override fun getSemesters(
        semesterNumber: Int?,
        academicStartYear: Int?,
        academicEndYear: Int?,
        branchId: Int?,
        schemeId: Int?
    ): Flow<PagingData<SemesterDto>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                GetSemestersPagingSource(
                    semesterApi = semesterApi,
                    semesterNumber = semesterNumber,
                    academicStartYear = academicStartYear,
                    academicEndYear = academicEndYear,
                    branchId = branchId,
                    schemeId = schemeId
                )
            }
        ).flow
    }

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