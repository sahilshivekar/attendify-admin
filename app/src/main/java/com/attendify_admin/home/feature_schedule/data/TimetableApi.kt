package com.attendify_admin.home.feature_schedule.data

import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.data.remote.dto.response.TimetableDto
import com.attendify_admin.home.feature_schedule.data.dto.request.AddTimetableRequest
import com.attendify_admin.home.feature_schedule.data.dto.request.UpdateTimetableRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface TimetableApi {

    @GET("api/v1/timetable/admin/get-timetables")
    suspend fun getTimetables(
        @Query("semesterNumber") semesterNumber: Int?,
        @Query("academicStartYearOfSemester") academicStartYearOfSemester: Int?,
        @Query("academicEndYearOfSemester") academicEndYearOfSemester: Int?,
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): Response<AttendifyApiResponse<List<TimetableDto>?>>

    @GET("api/v1/timetable/admin/get-timetable-by-id")
    suspend fun getTimetableById(@Query("timetableId") timetableId: Int): Response<AttendifyApiResponse<TimetableDto>>

    @POST("api/v1/timetable/admin/add-timetable")
    suspend fun addTimetable(@Body requestBody: AddTimetableRequest): Response<AttendifyApiResponse<TimetableDto>>

    @PUT("api/v1/timetable/admin/update-timetable")
    suspend fun updateTimetable(@Body requestBody: UpdateTimetableRequest): Response<AttendifyApiResponse<TimetableDto>>

    @DELETE("api/v1/timetable/admin/remove-timetable")
    suspend fun removeTimetable(@Query("timetableId") timetableId: Int): Response<AttendifyApiResponse<Unit>>
}