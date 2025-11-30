package com.presencify_admin.home.feature_academics.domain.repository


import com.presencify_admin.common.data.remote.dto.response.PresencifyApiResponse
import com.presencify_admin.common.data.remote.dto.response.SchemeDto
import com.presencify_admin.home.feature_academics.data.remote.dto.request.AddSchemeRequest
import com.presencify_admin.home.feature_academics.data.remote.dto.request.UpdateSchemeRequest
import retrofit2.Response

interface SchemeRepository {
    suspend fun getSchemes(searchQuery: String?): Response<PresencifyApiResponse<List<SchemeDto>?>>

    suspend fun addScheme(requestBody: AddSchemeRequest): Response<PresencifyApiResponse<SchemeDto?>>

    suspend fun updateScheme(requestBody: UpdateSchemeRequest): Response<PresencifyApiResponse<SchemeDto?>>

    suspend fun removeScheme(schemeId: Int): Response<PresencifyApiResponse<String?>>

    suspend fun getSchemeById(schemeId: Int): Response<PresencifyApiResponse<SchemeDto?>>
}