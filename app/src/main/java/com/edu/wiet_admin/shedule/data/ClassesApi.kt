package com.edu.wiet_admin.shedule.data

import com.edu.wiet_admin.common.data.remote.WietApiResponse
import com.edu.wiet_admin.shedule.data.dto.request.AddClassRequest
import com.edu.wiet_admin.shedule.data.dto.request.ExtendActiveTillDateRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query
import com.edu.wiet_admin.common.data.remote.response_dto.Class

interface ClassApi {

    @POST("api/v1/admin/add-class")
    suspend fun addClass(@Body requestBody: AddClassRequest): Response<WietApiResponse<Class>>

    @GET("api/v1/admin/get-classes")
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
        @Query("limit") limit: Int
    ): Response<WietApiResponse<List<Class>>>

    @GET("api/v1/admin/get-class-by-id")
    suspend fun getClassById(@Query("classId") classId: Int): Response<WietApiResponse<Class>>

    @PUT("api/v1/admin/extend-active-till-date-of-class")
    suspend fun extendActiveTillDateOfClass(@Body requestBody: ExtendActiveTillDateRequest): Response<WietApiResponse<Class>>

    @DELETE("api/v1/admin/remove-class")
    suspend fun removeClass(@Query("classId") classId: Int): Response<WietApiResponse<Unit>>
}