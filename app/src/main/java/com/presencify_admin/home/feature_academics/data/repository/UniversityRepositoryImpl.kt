package com.presencify_admin.home.feature_academics.data.repository

import com.presencify_admin.common.data.remote.dto.response.PresencifyApiResponse
import com.presencify_admin.common.data.remote.dto.response.UniversityDto
import com.presencify_admin.home.feature_academics.data.remote.UniversityApi
import com.presencify_admin.home.feature_academics.data.remote.dto.request.AddUniversityRequest
import com.presencify_admin.home.feature_academics.data.remote.dto.request.UpdateUniversityRequest
import com.presencify_admin.home.feature_academics.domain.repository.UniversityRepository
import retrofit2.Response
import javax.inject.Inject

class UniversityRepositoryImpl @Inject constructor(
    private val universityApi: UniversityApi
) : UniversityRepository {

    override suspend fun getUniversities(): Response<PresencifyApiResponse<List<UniversityDto>?>> =
        universityApi.getUniversities()

    override suspend fun addUniversity(requestBody: AddUniversityRequest): Response<PresencifyApiResponse<UniversityDto?>> =
        universityApi.addUniversity(requestBody)

    override suspend fun updateUniversity(requestBody: UpdateUniversityRequest): Response<PresencifyApiResponse<UniversityDto?>> =
        universityApi.updateUniversity(requestBody)

    override suspend fun removeUniversity(universityId: Int): Response<PresencifyApiResponse<String?>> =
        universityApi.removeUniversity(universityId)

    override suspend fun getUniversityById(universityId: Int): Response<PresencifyApiResponse<UniversityDto?>> =
        universityApi.getUniversityById(universityId)
}