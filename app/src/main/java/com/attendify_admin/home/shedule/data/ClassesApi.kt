package com.attendify_admin.home.shedule.data

import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.data.remote.response_dto.CancelledClass
import com.attendify_admin.common.data.remote.response_dto.Class
import com.attendify_admin.home.shedule.data.dto.request.AddClassRequest
import com.attendify_admin.home.shedule.data.dto.request.AddExtraClassRequest
import com.attendify_admin.home.shedule.data.dto.request.CancelClassRequest
import com.attendify_admin.home.shedule.data.dto.request.ExtendActiveTillDateRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface ClassApi {

    @POST("api/v1/class/add-class")
    suspend fun addClass(@Body requestBody: AddClassRequest): Response<AttendifyApiResponse<Class>>

    @GET("api/v1/class/get-classes")
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
    ): Response<AttendifyApiResponse<List<Class>>>

    @GET("api/v1/class/get-class-by-id")
    suspend fun getClassById(@Query("classId") classId: Int): Response<AttendifyApiResponse<Class>>

    @PUT("api/v1/class/extend-active-till-date-of-class")
    suspend fun extendActiveTillDateOfClass(@Body requestBody: ExtendActiveTillDateRequest): Response<AttendifyApiResponse<Class>>

    @DELETE("api/v1/class/remove-class")
    suspend fun removeClass(@Query("classId") classId: Int): Response<AttendifyApiResponse<Unit>>

    @POST("api/v1/class/cancel-class")
    suspend fun cancelClass(
        @Body requestBody: CancelClassRequest,
    ): Response<AttendifyApiResponse<Unit>>

    @POST("api/v1/class/add-extra-class")
    suspend fun addExtraClass(
        @Body requestBody: AddExtraClassRequest,
    ): Response<AttendifyApiResponse<Class>>

    @GET("api/v1/class/get-cancelled-classes")
    suspend fun getCancelledClasses(
        @Query("divisionId") divisionId: Int,
        @Query("batchId") batchId: Int,
        @Query("date") date: String,
        @Query("page") page: Int,
        @Query("limit") limit: Int,
    ): Response<AttendifyApiResponse<CancelledClass>>
}