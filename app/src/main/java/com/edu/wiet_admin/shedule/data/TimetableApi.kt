package com.edu.wiet_admin.shedule.data

import com.edu.wiet_admin.common.data.remote.WietApiResponse
import com.edu.wiet_admin.common.data.remote.response_dto.Timetable
import com.edu.wiet_admin.shedule.data.dto.request.AddTimetableRequest
import com.edu.wiet_admin.shedule.data.dto.request.UpdateTimetableRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface TimetableApi {

    @GET("api/v1/admin/get-timetables")
    suspend fun getTimetables(
        @Query("semesterNumber") semesterNumber: Int?,
        @Query("academicStartYearOfSemester") academicStartYearOfSemester: Int?,
        @Query("academicEndYearOfSemester") academicEndYearOfSemester: Int?,
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): Response<WietApiResponse<List<Timetable>>>

    @GET("api/v1/admin/get-timetable-by-id")
    suspend fun getTimetableById(@Query("timetableId") timetableId: Int): Response<WietApiResponse<Timetable>>

    @POST("api/v1/admin/add-timetable")
    suspend fun addTimetable(@Body requestBody: AddTimetableRequest): Response<WietApiResponse<Timetable>>

    @PUT("api/v1/admin/update-timetable")
    suspend fun updateTimetable(@Body requestBody: UpdateTimetableRequest): Response<WietApiResponse<Timetable>>

    @DELETE("api/v1/admin/remove-timetable")
    suspend fun removeTimetable(@Query("timetableId") timetableId: Int): Response<WietApiResponse<Unit>>
}