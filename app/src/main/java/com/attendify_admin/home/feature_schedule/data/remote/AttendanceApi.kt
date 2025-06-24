package com.attendify_admin.home.feature_schedule.data.remote

import com.attendify_admin.common.data.remote.dto.response.AttendanceAllStudentsDto
import com.attendify_admin.common.data.remote.dto.response.AttendanceDto
import com.attendify_admin.common.data.remote.dto.response.AttendanceStudentAggregatedAndDetailedAttendanceDto
import com.attendify_admin.common.data.remote.dto.response.AttendanceStudentDto
import com.attendify_admin.common.data.remote.dto.response.AttendifyApiResponse
import com.attendify_admin.common.data.remote.dto.response.NoParentEmailStudentsDto
import com.attendify_admin.home.feature_schedule.data.remote.dto.request.AddStudentsAttendanceRequest
import com.attendify_admin.home.feature_schedule.data.remote.dto.request.CreateAttendanceRequest
import com.attendify_admin.home.feature_schedule.data.remote.dto.request.MarkAttendanceRequest
import com.attendify_admin.home.feature_schedule.data.remote.dto.request.SendAttendanceReportRequest
import com.attendify_admin.home.feature_schedule.data.remote.dto.request.UpdateStudentAttendanceRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface AttendanceApi {

    @POST("api/v1/attendance/admin/create-attendance")
    suspend fun createAttendance(@Body requestBody: CreateAttendanceRequest): Response<AttendifyApiResponse<AttendanceDto?>>

    @POST("api/v1/attendance/admin/add-students-to-attendance")
    suspend fun addStudentsAttendance(@Body requestBody: AddStudentsAttendanceRequest): Response<AttendifyApiResponse<List<AttendanceStudentDto>?>>

    @PUT("api/v1/attendance/admin/update-student-attendance")
    suspend fun updateStudentAttendance(@Body requestBody: UpdateStudentAttendanceRequest): Response<AttendifyApiResponse<AttendanceStudentDto?>>

    @DELETE("api/v1/attendance/admin/remove-attendance")
    suspend fun removeAttendance(@Query("attendanceId") attendanceId: Int): Response<AttendifyApiResponse<String?>>

    @GET("api/v1/attendance/admin/get-attendance")
    suspend fun getAttendance(
        @Query("date") date: String?,
        @Query("attendanceId") attendanceId: Int?,
        @Query("classId") classId: Int?,
        @Query("studentId") studentId: Int?,
        @Query("courseId") courseId: Int?,
        @Query("semesterId") semesterId: Int?,
        @Query("divisionId") divisionId: Int?,
    ): Response<AttendifyApiResponse<List<AttendanceStudentDto>?>>

    @GET("api/v1/attendance/admin/get-attendance-of-student")
    suspend fun getAttendanceOfStudent(
        @Query("studentId") studentId: Int,
        @Query("courseId") courseId: Int,
        @Query("semesterId") semesterId: Int,
        @Query("divisionId") divisionId: Int,
        @Query("batchId") batchId: Int,
        @Query("startDate") startDate: String,
        @Query("endDate") endDate: String
    ): Response<AttendifyApiResponse<AttendanceStudentAggregatedAndDetailedAttendanceDto>>

    @GET("api/v1/attendance/admin/get-attendance-of-all")
    suspend fun getAttendanceOfAllForSemesterDivisionBatchCourse(
        @Query("courseId") courseId: Int,
        @Query("semesterId") semesterId: Int,
        @Query("divisionId") divisionId: Int,
        @Query("batchId") batchId: Int,
        @Query("startDate") startDate: String,
        @Query("endDate") endDate: String
    ): Response<AttendifyApiResponse<AttendanceAllStudentsDto>>

    @POST("api/v1/attendance/admin/mark-student-attendance")
    suspend fun markStudentAttendanceByBLEsessionUUID(
        @Body requestBody: MarkAttendanceRequest
    ): Response<AttendifyApiResponse<String?>>

    @POST("api/v1/attendance/admin/send-attendance-report")
    suspend fun sendAttendanceReport(
        @Body requestBody: SendAttendanceReportRequest
    ): Response<AttendifyApiResponse<List<NoParentEmailStudentsDto>?>>

    @GET("api/v1/attendance/admin/get-active-attendance-sheet")
    suspend fun getActiveAttendanceSheet(
        @Query("studentId") studentId: Int,
        @Query("divisionId") divisionId: Int
    ): Response<AttendifyApiResponse<List<AttendanceDto>?>>

}