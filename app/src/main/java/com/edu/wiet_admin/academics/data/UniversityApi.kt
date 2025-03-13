package com.edu.wiet_admin.academics.data

import com.edu.wiet_admin.academics.data.dto.request.AddUniversityRequest
import com.edu.wiet_admin.academics.data.dto.request.UpdateUniversityRequest
import com.edu.wiet_admin.common.data.remote.response_dto.University
import com.edu.wiet_admin.common.data.remote.WietApiResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query


interface UniversityApi {

    @GET("api/v1/admin/get-universities")
    suspend fun getUniversities(): Response<WietApiResponse<List<University?>>>

    @POST("api/v1/admin/add")
    suspend fun addUniversity(@Body requestBody: AddUniversityRequest): Response<WietApiResponse<University?>>

    @PUT("api/v1/admin/update")
    suspend fun updateUniversity(@Body requestBody: UpdateUniversityRequest): Response<WietApiResponse<University?>>

    @DELETE("api/v1/admin/remove")
    suspend fun removeUniversity(@Query("id") universityId: String): Response<WietApiResponse<String?>>

    @GET("api/v1/admin/get-university-by-id")
    suspend fun getUniversityById(@Query("universityId") universityId: String): Response<WietApiResponse<University?>>
}