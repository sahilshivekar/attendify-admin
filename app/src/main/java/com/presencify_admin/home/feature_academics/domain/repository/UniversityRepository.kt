package com.presencify_admin.home.feature_academics.domain.repository

import com.presencify_admin.common.data.remote.dto.response.PresencifyApiResponse
import com.presencify_admin.common.data.remote.dto.response.UniversityDto
import com.presencify_admin.home.feature_academics.data.remote.dto.request.AddUniversityRequest
import com.presencify_admin.home.feature_academics.data.remote.dto.request.UpdateUniversityRequest
import retrofit2.Response

interface UniversityRepository {
    suspend fun getUniversities(): Response<PresencifyApiResponse<List<UniversityDto>?>>

    suspend fun addUniversity(requestBody: AddUniversityRequest): Response<PresencifyApiResponse<UniversityDto?>>

    suspend fun updateUniversity(requestBody: UpdateUniversityRequest): Response<PresencifyApiResponse<UniversityDto?>>

    suspend fun removeUniversity(universityId: Int): Response<PresencifyApiResponse<String?>>

    suspend fun getUniversityById(universityId: Int): Response<PresencifyApiResponse<UniversityDto?>>
}