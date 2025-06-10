package com.attendify_admin.home.academics.domain.repository


import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.data.remote.response_dto.Scheme
import com.attendify_admin.home.academics.data.dto.request.AddSchemeRequest
import com.attendify_admin.home.academics.data.dto.request.UpdateSchemeRequest
import retrofit2.Response

interface SchemeRepository {
    suspend fun getSchemes(searchQuery: String?): Response<AttendifyApiResponse<List<Scheme?>>>

    suspend fun addScheme(requestBody: AddSchemeRequest): Response<AttendifyApiResponse<Scheme?>>

    suspend fun updateScheme(requestBody: UpdateSchemeRequest): Response<AttendifyApiResponse<Scheme?>>

    suspend fun removeScheme(schemeId: String): Response<AttendifyApiResponse<String?>>

    suspend fun getSchemeById(schemeId: String): Response<AttendifyApiResponse<Scheme?>>
}