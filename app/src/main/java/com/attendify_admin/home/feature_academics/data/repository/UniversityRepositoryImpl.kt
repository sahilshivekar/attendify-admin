package com.attendify_admin.home.feature_academics.data.repository

import com.attendify_admin.common.data.remote.dto.response.AttendifyApiResponse
import com.attendify_admin.common.data.remote.dto.response.UniversityDto
import com.attendify_admin.home.feature_academics.data.remote.UniversityApi
import com.attendify_admin.home.feature_academics.data.remote.dto.request.AddUniversityRequest
import com.attendify_admin.home.feature_academics.data.remote.dto.request.UpdateUniversityRequest
import com.attendify_admin.home.feature_academics.domain.repository.UniversityRepository
import retrofit2.Response
import javax.inject.Inject

class UniversityRepositoryImpl @Inject constructor(
    private val universityApi: UniversityApi
) : UniversityRepository {

    override suspend fun getUniversities(): Response<AttendifyApiResponse<List<UniversityDto>?>> =
        universityApi.getUniversities()

    override suspend fun addUniversity(requestBody: AddUniversityRequest): Response<AttendifyApiResponse<UniversityDto?>> =
        universityApi.addUniversity(requestBody)

    override suspend fun updateUniversity(requestBody: UpdateUniversityRequest): Response<AttendifyApiResponse<UniversityDto?>> =
        universityApi.updateUniversity(requestBody)

    override suspend fun removeUniversity(universityId: Int): Response<AttendifyApiResponse<String?>> =
        universityApi.removeUniversity(universityId)

    override suspend fun getUniversityById(universityId: Int): Response<AttendifyApiResponse<UniversityDto?>> =
        universityApi.getUniversityById(universityId)
}