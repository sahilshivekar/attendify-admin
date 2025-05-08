package com.attendify_admin.home.academics.data


import com.attendify_admin.home.academics.data.dto.request.AddSchemeRequest
import com.attendify_admin.home.academics.data.dto.request.UpdateSchemeRequest
import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.data.remote.response_dto.Scheme
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface SchemeApi {

    @GET("api/v1/scheme/get-schemes")
    suspend fun getSchemes(@Query("searchQuery") searchQuery: String?): Response<AttendifyApiResponse<List<Scheme?>>>

    @POST("api/v1/scheme/add")
    suspend fun addScheme(@Body requestBody: AddSchemeRequest): Response<AttendifyApiResponse<Scheme?>>

    @PUT("api/v1/scheme/update")
    suspend fun updateScheme(@Body requestBody: UpdateSchemeRequest): Response<AttendifyApiResponse<Scheme?>>

    @DELETE("api/v1/scheme/remove")
    suspend fun removeScheme(@Query("id") schemeId: String): Response<AttendifyApiResponse<String?>>

    @GET("api/v1/scheme/get-scheme-by-id")
    suspend fun getSchemeById(@Query("schemeId") schemeId: String): Response<AttendifyApiResponse<Scheme?>>
}