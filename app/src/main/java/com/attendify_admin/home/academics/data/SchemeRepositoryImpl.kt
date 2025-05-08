    package com.attendify_admin.home.academics.data


    import com.attendify_admin.home.academics.data.dto.request.AddSchemeRequest
    import com.attendify_admin.home.academics.data.dto.request.UpdateSchemeRequest
    import com.attendify_admin.home.academics.domain.repository.SchemeRepository
    import com.attendify_admin.common.data.remote.AttendifyApiResponse
    import com.attendify_admin.common.data.remote.response_dto.Scheme
    import retrofit2.Response
    import javax.inject.Inject

    class SchemeRepositoryImpl @Inject constructor(
        private val schemeApi: SchemeApi
    ) : SchemeRepository {

        override suspend fun getSchemes(searchQuery: String?): Response<AttendifyApiResponse<List<Scheme?>>> {
            return schemeApi.getSchemes(searchQuery)
        }

        override suspend fun addScheme(requestBody: AddSchemeRequest): Response<AttendifyApiResponse<Scheme?>> =
            schemeApi.addScheme(requestBody)

        override suspend fun updateScheme(requestBody: UpdateSchemeRequest): Response<AttendifyApiResponse<Scheme?>> =
            schemeApi.updateScheme(requestBody)

        override suspend fun removeScheme(schemeId: String): Response<AttendifyApiResponse<String?>> =
            schemeApi.removeScheme(schemeId)

        override suspend fun getSchemeById(schemeId: String): Response<AttendifyApiResponse<Scheme?>> =
            schemeApi.getSchemeById(schemeId)
    }