package com.presencify_admin.home.feature_academics.data.remote

import com.presencify_admin.common.data.remote.dto.response.PresencifyApiResponse
import com.presencify_admin.common.data.remote.dto.response.UniversityDto
import com.presencify_admin.home.feature_academics.data.remote.dto.request.AddUniversityRequest
import com.presencify_admin.home.feature_academics.data.remote.dto.request.UpdateUniversityRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query


interface UniversityApi {

    @GET("api/v1/university/admin/get-universities")
    suspend fun getUniversities(): Response<PresencifyApiResponse<List<UniversityDto>?>>

    @POST("api/v1/university/admin/add")
    suspend fun addUniversity(@Body requestBody: AddUniversityRequest): Response<PresencifyApiResponse<UniversityDto?>>

    @PUT("api/v1/university/admin/update")
    suspend fun updateUniversity(@Body requestBody: UpdateUniversityRequest): Response<PresencifyApiResponse<UniversityDto?>>

    @DELETE("api/v1/university/admin/remove")
    suspend fun removeUniversity(@Query("id") universityId: Int): Response<PresencifyApiResponse<String?>>

    @GET("api/v1/university/admin/get-university-by-id")
    suspend fun getUniversityById(@Query("universityId") universityId: Int): Response<PresencifyApiResponse<UniversityDto?>>
}