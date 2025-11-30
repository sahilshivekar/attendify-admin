package com.presencify_admin.home.feature_schedule.data.remote

import com.presencify_admin.common.data.remote.dto.response.PresencifyApiResponse
import com.presencify_admin.common.data.remote.dto.response.TimetableDto
import com.presencify_admin.common.data.remote.dto.response.TimetableListWithTotalCountDto
import com.presencify_admin.home.feature_schedule.data.remote.dto.request.AddTimetableRequest
import com.presencify_admin.home.feature_schedule.data.remote.dto.request.UpdateTimetableRequest
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
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 10,
        @Query("getAll") getAll: Boolean,
    ): Response<PresencifyApiResponse<TimetableListWithTotalCountDto>>


    @GET("api/v1/timetable/admin/get-timetable-by-id")
    suspend fun getTimetableById(@Query("timetableId") timetableId: Int): Response<PresencifyApiResponse<TimetableDto>>

    @POST("api/v1/timetable/admin/add-timetable")
    suspend fun addTimetable(@Body requestBody: AddTimetableRequest): Response<PresencifyApiResponse<TimetableDto>>

    @PUT("api/v1/timetable/admin/update-timetable")
    suspend fun updateTimetable(@Body requestBody: UpdateTimetableRequest): Response<PresencifyApiResponse<TimetableDto>>

    @DELETE("api/v1/timetable/admin/remove-timetable")
    suspend fun removeTimetable(@Query("timetableId") timetableId: Int): Response<PresencifyApiResponse<Unit>>
}