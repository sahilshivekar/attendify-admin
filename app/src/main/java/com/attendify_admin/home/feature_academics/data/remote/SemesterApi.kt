package com.attendify_admin.home.feature_academics.data.remote

import com.attendify_admin.common.data.remote.dto.response.AttendifyApiResponse
import com.attendify_admin.common.data.remote.dto.response.SemesterDto
import com.attendify_admin.common.data.remote.dto.response.SemesterListWithTotalCountDto
import com.attendify_admin.home.feature_academics.data.remote.dto.request.AddSemesterRequest
import com.attendify_admin.home.feature_academics.data.remote.dto.request.UpdateSemesterRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface SemesterApi {

    @GET("api/v1/semester/admin/get-semesters")
    suspend fun getSemesters(
        @Query("semesterNumber") semesterNumber: Int?,
        @Query("academicStartYear") academicStartYear: Int?,
        @Query("academicEndYear") academicEndYear: Int?,
        @Query("branchId") branchId: Int?,
        @Query("schemeId") schemeId: Int?,
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 10,
        @Query("getAll") getAll: Boolean,
    ): Response<AttendifyApiResponse<SemesterListWithTotalCountDto>>


    @POST("api/v1/semester/admin/add")
    suspend fun addSemester(@Body requestBody: AddSemesterRequest): Response<AttendifyApiResponse<SemesterDto?>>

    @PUT("api/v1/semester/admin/update")
    suspend fun updateSemester(@Body requestBody: UpdateSemesterRequest): Response<AttendifyApiResponse<SemesterDto?>>

    @DELETE("api/v1/semester/admin/remove")
    suspend fun removeSemester(@Query("id") semesterId: Int): Response<AttendifyApiResponse<String?>>

    @GET("api/v1/semester/admin/get-courses-of-semester")
    suspend fun getCoursesOfSemester(@Query("semesterId") semesterId: Int): Response<AttendifyApiResponse<List<SemesterDto>?>>

    @GET("api/v1/semester/admin/get-semester-by-id")
    suspend fun getSemesterById(@Query("semesterId") semesterId: Int): Response<AttendifyApiResponse<SemesterDto?>>
}