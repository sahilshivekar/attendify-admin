package com.attendify_admin.home.academics.data

import com.attendify_admin.home.academics.data.dto.request.AddUniversityRequest
import com.attendify_admin.home.academics.data.dto.request.UpdateUniversityRequest
import com.attendify_admin.common.data.remote.response_dto.University
import com.attendify_admin.common.data.remote.AttendifyApiResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query


interface UniversityApi {

    @GET("api/v1/university/get-universities")
    suspend fun getUniversities(): Response<AttendifyApiResponse<List<University?>>>

    @POST("api/v1/university/add")
    suspend fun addUniversity(@Body requestBody: AddUniversityRequest): Response<AttendifyApiResponse<University?>>

    @PUT("api/v1/university/update")
    suspend fun updateUniversity(@Body requestBody: UpdateUniversityRequest): Response<AttendifyApiResponse<University?>>

    @DELETE("api/v1/university/remove")
    suspend fun removeUniversity(@Query("id") universityId: String): Response<AttendifyApiResponse<String?>>

    @GET("api/v1/university/get-university-by-id")
    suspend fun getUniversityById(@Query("universityId") universityId: String): Response<AttendifyApiResponse<University?>>
}