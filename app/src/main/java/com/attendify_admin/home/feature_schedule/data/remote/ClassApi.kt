package com.attendify_admin.home.feature_schedule.data.remote

import com.attendify_admin.common.data.remote.dto.response.AttendifyApiResponse
import com.attendify_admin.common.data.remote.dto.response.CancelledClassListWithTotalCountDto
import com.attendify_admin.common.data.remote.dto.response.ClassDto
import com.attendify_admin.common.data.remote.dto.response.ClassListWithTotalCountDto
import com.attendify_admin.home.feature_schedule.data.remote.dto.request.AddClassRequest
import com.attendify_admin.home.feature_schedule.data.remote.dto.request.AddExtraClassRequest
import com.attendify_admin.home.feature_schedule.data.remote.dto.request.CancelClassRequest
import com.attendify_admin.home.feature_schedule.data.remote.dto.request.ExtendActiveTillDateRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface ClassApi {

    @POST("api/v1/class/admin/admin/add-class")
    suspend fun addClass(@Body requestBody: AddClassRequest): Response<AttendifyApiResponse<ClassDto>>

    @GET("api/v1/class/admin/get-classes")
    suspend fun getClasses(
        @Query("searchQuery") searchQuery: String?,
        @Query("timetableId") timetableId: Int?,
        @Query("divisionId") divisionId: Int?,
        @Query("startTime") startTime: String?,
        @Query("endTime") endTime: String?,
        @Query("activeFrom") activeFrom: String?,
        @Query("activeTill") activeTill: String?,
        @Query("instructorId") instructorId: Int?,
        @Query("dayOfWeek") dayOfWeek: String?,
        @Query("roomId") roomId: Int?,
        @Query("batchId") batchId: Int?,
        @Query("classType") classType: String?,
        @Query("courseId") courseId: Int?,
        @Query("semesterId") semesterId: Int?,
        @Query("page") page: Int,
        @Query("limit") limit: Int,
    ): Response<AttendifyApiResponse<ClassListWithTotalCountDto>>

    @GET("api/v1/class/admin/get-class-by-id")
    suspend fun getClassById(@Query("classId") classId: Int): Response<AttendifyApiResponse<ClassDto>>

    @PUT("api/v1/class/admin/extend-active-till-date-of-class")
    suspend fun extendActiveTillDateOfClass(@Body requestBody: ExtendActiveTillDateRequest): Response<AttendifyApiResponse<ClassDto>>

    @DELETE("api/v1/class/admin/remove-class")
    suspend fun removeClass(@Query("classId") classId: Int): Response<AttendifyApiResponse<Unit>>

    @POST("api/v1/class/admin/cancel-class")
    suspend fun cancelClass(
        @Body requestBody: CancelClassRequest,
    ): Response<AttendifyApiResponse<Unit>>

    @POST("api/v1/class/admin/add-extra-class")
    suspend fun addExtraClass(
        @Body requestBody: AddExtraClassRequest,
    ): Response<AttendifyApiResponse<ClassDto>>

    @GET("api/v1/class/admin/get-cancelled-classes")
    suspend fun getCancelledClasses(
        @Query("divisionId") divisionId: Int,
        @Query("batchId") batchId: Int,
        @Query("date") date: String,
        @Query("page") page: Int,
        @Query("limit") limit: Int,
    ): Response<AttendifyApiResponse<CancelledClassListWithTotalCountDto>>
}