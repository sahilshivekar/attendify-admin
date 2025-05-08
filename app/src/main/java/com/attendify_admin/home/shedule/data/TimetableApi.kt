package com.attendify_admin.home.shedule.data

import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.data.remote.response_dto.Timetable
import com.attendify_admin.home.shedule.data.dto.request.AddTimetableRequest
import com.attendify_admin.home.shedule.data.dto.request.UpdateTimetableRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface TimetableApi {

    @GET("api/v1/timetable/get-timetables")
    suspend fun getTimetables(
        @Query("semesterNumber") semesterNumber: Int?,
        @Query("academicStartYearOfSemester") academicStartYearOfSemester: Int?,
        @Query("academicEndYearOfSemester") academicEndYearOfSemester: Int?,
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): Response<AttendifyApiResponse<List<Timetable>>>

    @GET("api/v1/timetable/get-timetable-by-id")
    suspend fun getTimetableById(@Query("timetableId") timetableId: Int): Response<AttendifyApiResponse<Timetable>>

    @POST("api/v1/timetable/add-timetable")
    suspend fun addTimetable(@Body requestBody: AddTimetableRequest): Response<AttendifyApiResponse<Timetable>>

    @PUT("api/v1/timetable/update-timetable")
    suspend fun updateTimetable(@Body requestBody: UpdateTimetableRequest): Response<AttendifyApiResponse<Timetable>>

    @DELETE("api/v1/timetable/remove-timetable")
    suspend fun removeTimetable(@Query("timetableId") timetableId: Int): Response<AttendifyApiResponse<Unit>>
}