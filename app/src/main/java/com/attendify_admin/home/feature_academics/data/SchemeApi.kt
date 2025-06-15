package com.attendify_admin.home.feature_academics.data


import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.data.remote.dto.response.SchemeDto
import com.attendify_admin.home.feature_academics.data.dto.request.AddSchemeRequest
import com.attendify_admin.home.feature_academics.data.dto.request.UpdateSchemeRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface SchemeApi {

    @GET("api/v1/scheme/admin/get-schemes")
    suspend fun getSchemes(@Query("searchQuery") searchQuery: String?): Response<AttendifyApiResponse<List<SchemeDto>?>>

    @POST("api/v1/scheme/admin/add")
    suspend fun addScheme(@Body requestBody: AddSchemeRequest): Response<AttendifyApiResponse<SchemeDto?>>

    @PUT("api/v1/scheme/admin/update")
    suspend fun updateScheme(@Body requestBody: UpdateSchemeRequest): Response<AttendifyApiResponse<SchemeDto?>>

    @DELETE("api/v1/scheme/admin/remove")
    suspend fun removeScheme(@Query("id") schemeId: Int): Response<AttendifyApiResponse<String?>>

    @GET("api/v1/scheme/admin/get-scheme-by-id")
    suspend fun getSchemeById(@Query("schemeId") schemeId: Int): Response<AttendifyApiResponse<SchemeDto?>>
}