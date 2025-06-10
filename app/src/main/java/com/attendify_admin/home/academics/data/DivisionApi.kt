package com.attendify_admin.home.academics.data


import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.data.dto.response.Division
import com.attendify_admin.home.academics.data.dto.request.AddDivisionRequest
import com.attendify_admin.home.academics.data.dto.request.UpdateDivisionRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query


interface DivisionApi {

    @GET("api/v1/division/admin/get-divisions")
    suspend fun getDivisions(
        @Query("semesterNumber") semesterNumber: Int?,
        @Query("branchId") branchId: Int?,
        @Query("academicStartYear") academicStartYear: Int?,
        @Query("academicEndYear") academicEndYear: Int?,
        @Query("searchQuery") searchQuery: String?,
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): Response<AttendifyApiResponse<List<Division?>>>

    @POST("api/v1/division/admin/add")
    suspend fun addDivision(@Body requestBody: AddDivisionRequest): Response<AttendifyApiResponse<Division?>>

    @PUT("api/v1/division/admin/update")
    suspend fun updateDivision(@Body requestBody: UpdateDivisionRequest): Response<AttendifyApiResponse<Division?>>

    @DELETE("api/v1/division/admin/remove")
    suspend fun removeDivision(@Query("id") divisionId: Int): Response<AttendifyApiResponse<String?>>

    @GET("api/v1/division/admin/get-division-by-id")
    suspend fun getDivisionById(@Query("divisionId") divisionId: Int): Response<AttendifyApiResponse<Division?>>
}