package com.edu.wiet_admin.academics.data


import com.edu.wiet_admin.academics.data.dto.request.AddDivisionRequest
import com.edu.wiet_admin.academics.data.dto.request.UpdateDivisionRequest
import com.edu.wiet_admin.common.data.remote.WietApiResponse
import com.edu.wiet_admin.common.data.remote.response_dto.Division
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
    ): Response<WietApiResponse<List<Division?>>>

    @POST("api/v1/division/add")
    suspend fun addDivision(@Body requestBody: AddDivisionRequest): Response<WietApiResponse<Division?>>

    @PUT("api/v1/division/update")
    suspend fun updateDivision(@Body requestBody: UpdateDivisionRequest): Response<WietApiResponse<Division?>>

    @DELETE("api/v1/division/remove")
    suspend fun removeDivision(@Query("id") divisionId: String): Response<WietApiResponse<String?>>

    @GET("api/v1/division/get-division-by-id")
    suspend fun getDivisionById(@Query("divisionId") divisionId: String): Response<WietApiResponse<Division?>>
}