package com.attendify_admin.home.academics.data


import com.attendify_admin.home.academics.data.dto.request.AddDivisionRequest
import com.attendify_admin.home.academics.data.dto.request.UpdateDivisionRequest
import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.data.remote.response_dto.Division
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query


interface DivisionApi {

    @GET("api/v1/division/get-divisions")
    suspend fun getDivisions(
        @Query("semesterNumber") semesterNumber: Int?,
        @Query("branchId") branchId: String?,
        @Query("academicStartYear") academicStartYear: Int?,
        @Query("academicEndYear") academicEndYear: Int?,
        @Query("searchQuery") searchQuery: String?,
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): Response<AttendifyApiResponse<List<Division?>>>

    @POST("api/v1/division/add")
    suspend fun addDivision(@Body requestBody: AddDivisionRequest): Response<AttendifyApiResponse<Division?>>

    @PUT("api/v1/division/update")
    suspend fun updateDivision(@Body requestBody: UpdateDivisionRequest): Response<AttendifyApiResponse<Division?>>

    @DELETE("api/v1/division/remove")
    suspend fun removeDivision(@Query("id") divisionId: String): Response<AttendifyApiResponse<String?>>

    @GET("api/v1/division/get-division-by-id")
    suspend fun getDivisionById(@Query("divisionId") divisionId: String): Response<AttendifyApiResponse<Division?>>
}