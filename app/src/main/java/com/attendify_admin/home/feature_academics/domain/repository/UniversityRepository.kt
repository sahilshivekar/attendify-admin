package com.attendify_admin.home.feature_academics.domain.repository

import com.attendify_admin.common.data.remote.dto.response.AttendifyApiResponse
import com.attendify_admin.common.data.remote.dto.response.UniversityDto
import com.attendify_admin.home.feature_academics.data.remote.dto.request.AddUniversityRequest
import com.attendify_admin.home.feature_academics.data.remote.dto.request.UpdateUniversityRequest
import retrofit2.Response

interface UniversityRepository {
    suspend fun getUniversities(): Response<AttendifyApiResponse<List<UniversityDto>?>>

    suspend fun addUniversity(requestBody: AddUniversityRequest): Response<AttendifyApiResponse<UniversityDto?>>

    suspend fun updateUniversity(requestBody: UpdateUniversityRequest): Response<AttendifyApiResponse<UniversityDto?>>

    suspend fun removeUniversity(universityId: Int): Response<AttendifyApiResponse<String?>>

    suspend fun getUniversityById(universityId: Int): Response<AttendifyApiResponse<UniversityDto?>>
}