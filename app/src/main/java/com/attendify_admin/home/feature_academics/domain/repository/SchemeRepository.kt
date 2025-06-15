package com.attendify_admin.home.feature_academics.domain.repository


import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.data.remote.dto.response.SchemeDto
import com.attendify_admin.home.feature_academics.data.dto.request.AddSchemeRequest
import com.attendify_admin.home.feature_academics.data.dto.request.UpdateSchemeRequest
import retrofit2.Response

interface SchemeRepository {
    suspend fun getSchemes(searchQuery: String?): Response<AttendifyApiResponse<List<SchemeDto>?>>

    suspend fun addScheme(requestBody: AddSchemeRequest): Response<AttendifyApiResponse<SchemeDto?>>

    suspend fun updateScheme(requestBody: UpdateSchemeRequest): Response<AttendifyApiResponse<SchemeDto?>>

    suspend fun removeScheme(schemeId: Int): Response<AttendifyApiResponse<String?>>

    suspend fun getSchemeById(schemeId: Int): Response<AttendifyApiResponse<SchemeDto?>>
}