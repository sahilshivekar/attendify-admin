package com.edu.wiet_admin.shedule.data

import com.edu.wiet_admin.common.data.remote.WietApiResponse
import com.edu.wiet_admin.common.data.remote.response_dto.Attendance
import com.edu.wiet_admin.common.data.remote.response_dto.AttendanceStudent
import com.edu.wiet_admin.common.data.remote.response_dto.AttendanceStudentCount
import com.edu.wiet_admin.shedule.data.dto.request.AddStudentsAttendanceRequest
import com.edu.wiet_admin.shedule.data.dto.request.CreateAttendanceRequest
import com.edu.wiet_admin.shedule.data.dto.request.UpdateStudentAttendanceRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface AttendanceApi {

    @POST("api/v1/attendance/create-attendance")
    suspend fun createAttendance(@Body requestBody: CreateAttendanceRequest): Response<WietApiResponse<Attendance?>>

    @POST("api/v1/attendance/add-students-to-attendance")
    suspend fun addStudentsAttendance(@Body requestBody: AddStudentsAttendanceRequest): Response<WietApiResponse<List<AttendanceStudent?>>>

    @PUT("api/v1/attendance/update-student-attendance")
    suspend fun updateStudentAttendance(@Body requestBody: UpdateStudentAttendanceRequest): Response<WietApiResponse<AttendanceStudent?>>

    @DELETE("api/v1/attendance/remove-attendance")
    suspend fun removeAttendance(@Query("attendanceId") attendanceId: String): Response<WietApiResponse<String?>>

    @GET("api/v1/attendance/get-attendance")
    suspend fun getAttendance(
        @Query("date") date: String?,
        @Query("attendanceId") attendanceId: String?,
        @Query("classId") classId: String?,
        @Query("studentId") studentId: String?,
        @Query("courseId") courseId: String?,
        @Query("semesterId") semesterId: String?,
        @Query("divisionId") divisionId: String?
    ): Response<WietApiResponse<List<AttendanceStudent?>>>

    @GET("api/v1/attendance/get-attendance-of-student-for-specific-semester")
    suspend fun getAttendanceOfStudentForSpecificCourseInSemester(
        @Query("studentId") studentId: String,
        @Query("courseId") courseId: String,
        @Query("semesterId") semesterId: String
    ): Response<WietApiResponse<List<AttendanceStudentCount?>>>

    @GET("api/v1/attendance/get-attendance-date-course")
    suspend fun getAttendanceOfCourseOnDate(
        @Query("date") date: String,
        @Query("courseId") courseId: String,
        @Query("divisionId") divisionId: String
    ): Response<WietApiResponse<List<AttendanceStudentCount?>>>

    @GET("api/v1/attendance/get-attendance-course-division")
    suspend fun getAttendanceOfCourseThroughoutSemester(
        @Query("courseId") courseId: String,
        @Query("divisionId") divisionId: String
    ): Response<WietApiResponse<List<AttendanceStudentCount?>>>
}