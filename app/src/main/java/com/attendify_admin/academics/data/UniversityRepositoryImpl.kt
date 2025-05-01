package com.attendify_admin.academics.data

import com.attendify_admin.academics.data.dto.request.AddUniversityRequest
import com.attendify_admin.academics.data.dto.request.UpdateUniversityRequest
import com.attendify_admin.academics.domain.repository.UniversityRepository
import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.data.remote.response_dto.University
import retrofit2.Response
import javax.inject.Inject

class UniversityRepositoryImpl @Inject constructor(
    private val universityApi: UniversityApi
) : UniversityRepository {

    override suspend fun getUniversities(): Response<AttendifyApiResponse<List<University?>>> =
        universityApi.getUniversities()

    override suspend fun addUniversity(requestBody: AddUniversityRequest): Response<AttendifyApiResponse<University?>> =
        universityApi.addUniversity(requestBody)

    override suspend fun updateUniversity(requestBody: UpdateUniversityRequest): Response<AttendifyApiResponse<University?>> =
        universityApi.updateUniversity(requestBody)

    override suspend fun removeUniversity(universityId: String): Response<AttendifyApiResponse<String?>> =
        universityApi.removeUniversity(universityId)

    override suspend fun getUniversityById(universityId: String): Response<AttendifyApiResponse<University?>> =
        universityApi.getUniversityById(universityId)
}