package com.edu.wiet_admin.academics.data

import com.edu.wiet_admin.academics.data.dto.request.AddSemesterRequest
import com.edu.wiet_admin.academics.data.dto.request.UpdateSemesterRequest
import com.edu.wiet_admin.common.data.remote.WietApiResponse
import com.edu.wiet_admin.common.data.remote.response_dto.Semester
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface SemesterApi {

    @GET("api/v1/semester/get-semesters")
    suspend fun getSemesters(
        @Query("semesterNumber") semesterNumber: Int?,
        @Query("academicStartYear") academicStartYear: Int?,
        @Query("academicEndYear") academicEndYear: Int?,
        @Query("branchId") branchId: String?,
        @Query("schemeId") schemeId: String?,
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): Response<WietApiResponse<List<Semester?>>>

    @POST("api/v1/semester/add")
    suspend fun addSemester(@Body requestBody: AddSemesterRequest): Response<WietApiResponse<Semester?>>

    @PUT("api/v1/semester/update")
    suspend fun updateSemester(@Body requestBody: UpdateSemesterRequest): Response<WietApiResponse<Semester?>>

    @DELETE("api/v1/semester/remove")
    suspend fun removeSemester(@Query("id") semesterId: String): Response<WietApiResponse<String?>>

    @GET("api/v1/semester/get-courses-of-semester")
    suspend fun getCoursesOfSemester(@Query("semesterId") semesterId: String): Response<WietApiResponse<List<Semester?>>>

    @GET("api/v1/semester/get-semester-by-id")
    suspend fun getSemesterById(@Query("semesterId") semesterId: String): Response<WietApiResponse<Semester?>>
}