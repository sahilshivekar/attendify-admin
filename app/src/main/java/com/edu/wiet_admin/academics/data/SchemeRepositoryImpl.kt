package com.edu.wiet_admin.academics.data


import com.edu.wiet_admin.academics.data.dto.request.AddSchemeRequest
import com.edu.wiet_admin.academics.data.dto.request.UpdateSchemeRequest
import com.edu.wiet_admin.academics.domain.repository.SchemeRepository
import com.edu.wiet_admin.common.data.remote.WietApiResponse
import com.edu.wiet_admin.common.data.remote.response_dto.Scheme
import retrofit2.Response
import javax.inject.Inject

class SchemeRepositoryImpl @Inject constructor(
    private val schemeApi: SchemeApi
) : SchemeRepository {

    override suspend fun getSchemes(searchQuery: String?): Response<WietApiResponse<List<Scheme?>>> =
        schemeApi.getSchemes(searchQuery)

    override suspend fun addScheme(requestBody: AddSchemeRequest): Response<WietApiResponse<Scheme?>> =
        schemeApi.addScheme(requestBody)

    override suspend fun updateScheme(requestBody: UpdateSchemeRequest): Response<WietApiResponse<Scheme?>> =
        schemeApi.updateScheme(requestBody)

    override suspend fun removeScheme(schemeId: String): Response<WietApiResponse<String?>> =
        schemeApi.removeScheme(schemeId)

    override suspend fun getSchemeById(schemeId: String): Response<WietApiResponse<Scheme?>> =
        schemeApi.getSchemeById(schemeId)
}