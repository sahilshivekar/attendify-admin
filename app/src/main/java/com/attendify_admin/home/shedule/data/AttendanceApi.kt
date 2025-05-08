package com.attendify_admin.home.shedule.data

import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.data.remote.response_dto.Attendance
import com.attendify_admin.common.data.remote.response_dto.AttendanceStudent
import com.attendify_admin.common.data.remote.response_dto.AttendanceStudentCount
import com.attendify_admin.home.shedule.data.dto.request.AddStudentsAttendanceRequest
import com.attendify_admin.home.shedule.data.dto.request.CreateAttendanceRequest
import com.attendify_admin.home.shedule.data.dto.request.UpdateStudentAttendanceRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface AttendanceApi {

    @POST("api/v1/attendance/create-attendance")
    suspend fun createAttendance(@Body requestBody: CreateAttendanceRequest): Response<AttendifyApiResponse<Attendance?>>

    @POST("api/v1/attendance/add-students-to-attendance")
    suspend fun addStudentsAttendance(@Body requestBody: AddStudentsAttendanceRequest): Response<AttendifyApiResponse<List<AttendanceStudent?>>>

    @PUT("api/v1/attendance/update-student-attendance")
    suspend fun updateStudentAttendance(@Body requestBody: UpdateStudentAttendanceRequest): Response<AttendifyApiResponse<AttendanceStudent?>>

    @DELETE("api/v1/attendance/remove-attendance")
    suspend fun removeAttendance(@Query("attendanceId") attendanceId: String): Response<AttendifyApiResponse<String?>>

    @GET("api/v1/attendance/get-attendance")
    suspend fun getAttendance(
        @Query("date") date: String?,
        @Query("attendanceId") attendanceId: String?,
        @Query("classId") classId: String?,
        @Query("studentId") studentId: String?,
        @Query("courseId") courseId: String?,
        @Query("semesterId") semesterId: String?,
        @Query("divisionId") divisionId: String?
    ): Response<AttendifyApiResponse<List<AttendanceStudent?>>>

    @GET("api/v1/attendance/get-attendance-of-student-for-specific-semester")
    suspend fun getAttendanceOfStudentForSpecificCourseInSemester(
        @Query("studentId") studentId: String,
        @Query("courseId") courseId: String,
        @Query("semesterId") semesterId: String
    ): Response<AttendifyApiResponse<List<AttendanceStudentCount?>>>

    @GET("api/v1/attendance/get-attendance-date-course")
    suspend fun getAttendanceOfCourseOnDate(
        @Query("date") date: String,
        @Query("courseId") courseId: String,
        @Query("divisionId") divisionId: String
    ): Response<AttendifyApiResponse<List<AttendanceStudentCount?>>>

    @GET("api/v1/attendance/get-attendance-course-division")
    suspend fun getAttendanceOfCourseThroughoutSemester(
        @Query("courseId") courseId: String,
        @Query("divisionId") divisionId: String
    ): Response<AttendifyApiResponse<List<AttendanceStudentCount?>>>
}