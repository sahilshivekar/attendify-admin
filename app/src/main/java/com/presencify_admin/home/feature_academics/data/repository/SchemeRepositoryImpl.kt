package com.presencify_admin.home.feature_academics.data.repository


    import com.presencify_admin.common.data.remote.dto.response.PresencifyApiResponse
    import com.presencify_admin.common.data.remote.dto.response.SchemeDto
    import com.presencify_admin.home.feature_academics.data.remote.SchemeApi
    import com.presencify_admin.home.feature_academics.data.remote.dto.request.AddSchemeRequest
    import com.presencify_admin.home.feature_academics.data.remote.dto.request.UpdateSchemeRequest
    import com.presencify_admin.home.feature_academics.domain.repository.SchemeRepository
    import retrofit2.Response
    import javax.inject.Inject

    class SchemeRepositoryImpl @Inject constructor(
        private val schemeApi: SchemeApi
    ) : SchemeRepository {

        override suspend fun getSchemes(searchQuery: String?): Response<PresencifyApiResponse<List<SchemeDto>?>> {
            return schemeApi.getSchemes(searchQuery)
        }

        override suspend fun addScheme(requestBody: AddSchemeRequest): Response<PresencifyApiResponse<SchemeDto?>> =
            schemeApi.addScheme(requestBody)

        override suspend fun updateScheme(requestBody: UpdateSchemeRequest): Response<PresencifyApiResponse<SchemeDto?>> =
            schemeApi.updateScheme(requestBody)

        override suspend fun removeScheme(schemeId: Int): Response<PresencifyApiResponse<String?>> =
            schemeApi.removeScheme(schemeId)

        override suspend fun getSchemeById(schemeId: Int): Response<PresencifyApiResponse<SchemeDto?>> =
            schemeApi.getSchemeById(schemeId)
    }