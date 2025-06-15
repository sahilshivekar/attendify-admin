package com.attendify_admin.home.feature_academics.data

import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.data.remote.dto.response.UniversityDto
import com.attendify_admin.home.feature_academics.data.dto.request.AddUniversityRequest
import com.attendify_admin.home.feature_academics.data.dto.request.UpdateUniversityRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query


interface UniversityApi {

    @GET("api/v1/university/admin/get-universities")
    suspend fun getUniversities(): Response<AttendifyApiResponse<List<UniversityDto>?>>

    @POST("api/v1/university/admin/add")
    suspend fun addUniversity(@Body requestBody: AddUniversityRequest): Response<AttendifyApiResponse<UniversityDto?>>

    @PUT("api/v1/university/admin/update")
    suspend fun updateUniversity(@Body requestBody: UpdateUniversityRequest): Response<AttendifyApiResponse<UniversityDto?>>

    @DELETE("api/v1/university/admin/remove")
    suspend fun removeUniversity(@Query("id") universityId: Int): Response<AttendifyApiResponse<String?>>

    @GET("api/v1/university/admin/get-university-by-id")
    suspend fun getUniversityById(@Query("universityId") universityId: Int): Response<AttendifyApiResponse<UniversityDto?>>
}