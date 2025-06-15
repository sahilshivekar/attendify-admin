    package com.attendify_admin.home.feature_academics.data


    import com.attendify_admin.common.data.remote.AttendifyApiResponse
    import com.attendify_admin.common.data.remote.dto.response.SchemeDto
    import com.attendify_admin.home.feature_academics.data.dto.request.AddSchemeRequest
    import com.attendify_admin.home.feature_academics.data.dto.request.UpdateSchemeRequest
    import com.attendify_admin.home.feature_academics.domain.repository.SchemeRepository
    import retrofit2.Response
    import javax.inject.Inject

    class SchemeRepositoryImpl @Inject constructor(
        private val schemeApi: SchemeApi
    ) : SchemeRepository {

        override suspend fun getSchemes(searchQuery: String?): Response<AttendifyApiResponse<List<SchemeDto>?>> {
            return schemeApi.getSchemes(searchQuery)
        }

        override suspend fun addScheme(requestBody: AddSchemeRequest): Response<AttendifyApiResponse<SchemeDto?>> =
            schemeApi.addScheme(requestBody)

        override suspend fun updateScheme(requestBody: UpdateSchemeRequest): Response<AttendifyApiResponse<SchemeDto?>> =
            schemeApi.updateScheme(requestBody)

        override suspend fun removeScheme(schemeId: Int): Response<AttendifyApiResponse<String?>> =
            schemeApi.removeScheme(schemeId)

        override suspend fun getSchemeById(schemeId: Int): Response<AttendifyApiResponse<SchemeDto?>> =
            schemeApi.getSchemeById(schemeId)
    }