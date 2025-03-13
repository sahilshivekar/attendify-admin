package com.edu.wiet_admin.academics.data


import com.edu.wiet_admin.academics.data.dto.request.AddSchemeRequest
import com.edu.wiet_admin.academics.data.dto.request.UpdateSchemeRequest
import com.edu.wiet_admin.common.data.remote.WietApiResponse
import com.edu.wiet_admin.common.data.remote.response_dto.Scheme
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface SchemeApi {

    @GET("api/v1/admin/get-schemes")
    suspend fun getSchemes(@Query("searchQuery") searchQuery: String?): Response<WietApiResponse<List<Scheme?>>>

    @POST("api/v1/admin/add")
    suspend fun addScheme(@Body requestBody: AddSchemeRequest): Response<WietApiResponse<Scheme?>>

    @PUT("api/v1/admin/update")
    suspend fun updateScheme(@Body requestBody: UpdateSchemeRequest): Response<WietApiResponse<Scheme?>>

    @DELETE("api/v1/admin/remove")
    suspend fun removeScheme(@Query("id") schemeId: String): Response<WietApiResponse<String?>>

    @GET("api/v1/admin/get-scheme-by-id")
    suspend fun getSchemeById(@Query("schemeId") schemeId: String): Response<WietApiResponse<Scheme?>>
}