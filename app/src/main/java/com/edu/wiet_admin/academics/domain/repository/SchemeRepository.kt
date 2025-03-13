package com.edu.wiet_admin.academics.domain.repository


import com.edu.wiet_admin.academics.data.dto.request.AddSchemeRequest
import com.edu.wiet_admin.academics.data.dto.request.UpdateSchemeRequest
import com.edu.wiet_admin.common.data.remote.WietApiResponse
import com.edu.wiet_admin.common.data.remote.response_dto.Scheme
import retrofit2.Response

interface SchemeRepository {
    suspend fun getSchemes(searchQuery: String?): Response<WietApiResponse<List<Scheme?>>>

    suspend fun addScheme(requestBody: AddSchemeRequest): Response<WietApiResponse<Scheme?>>

    suspend fun updateScheme(requestBody: UpdateSchemeRequest): Response<WietApiResponse<Scheme?>>

    suspend fun removeScheme(schemeId: String): Response<WietApiResponse<String?>>

    suspend fun getSchemeById(schemeId: String): Response<WietApiResponse<Scheme?>>
}