package com.attendify_admin.home.shedule.domain.repository

import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.data.remote.response_dto.Attendance
import com.attendify_admin.common.data.remote.response_dto.AttendanceAllStudents
import com.attendify_admin.common.data.remote.response_dto.AttendanceStudent
import com.attendify_admin.common.data.remote.response_dto.AttendanceStudentAggregatedAndDetailedAttendance
import com.attendify_admin.common.data.remote.response_dto.NoParentEmailStudents
import com.attendify_admin.home.shedule.data.dto.request.AddStudentsAttendanceRequest
import com.attendify_admin.home.shedule.data.dto.request.CreateAttendanceRequest
import com.attendify_admin.home.shedule.data.dto.request.RemoveAttendanceRequest
import com.attendify_admin.home.shedule.data.dto.request.UpdateStudentAttendanceRequest
import retrofit2.Response

interface AttendanceRepository {
    suspend fun createAttendance(requestBody: CreateAttendanceRequest): Response<AttendifyApiResponse<Attendance?>>

    suspend fun addStudentsAttendance(requestBody: AddStudentsAttendanceRequest): Response<AttendifyApiResponse<List<AttendanceStudent?>>>

    suspend fun updateStudentAttendance(requestBody: UpdateStudentAttendanceRequest): Response<AttendifyApiResponse<AttendanceStudent?>>

    suspend fun removeAttendance(requestBody: RemoveAttendanceRequest): Response<AttendifyApiResponse<String?>>

    suspend fun getAttendance(
        date: String?,
        attendanceId: String?,
        classId: String?,
        studentId: String?,
        courseId: String?,
        semesterId: String?,
        divisionId: String?,
    ): Response<AttendifyApiResponse<List<AttendanceStudent?>>>

    suspend fun getAttendanceOfStudent(
        studentId: String,
        courseId: String,
        semesterId: String,
        divisionId: String,
        batchId: String,
        startDate: String,
        endDate: String,
    ): Response<AttendifyApiResponse<AttendanceStudentAggregatedAndDetailedAttendance>>

    suspend fun getAttendanceOfAllForSemesterDivisionBatchCourse(
        courseId: String,
        semesterId: String,
        divisionId: String,
        batchId: String,
        startDate: String,
        endDate: String,
    ): Response<AttendifyApiResponse<AttendanceAllStudents>>

    suspend fun markStudentAttendanceByBLEsessionUUID(
        bleSessionUUID: String,
        studentId: Int,
    ): Response<AttendifyApiResponse<String?>>

    suspend fun sendAttendanceReport(
        startDate: String,
        endDate: String,
        studentIds: List<String>,
        courseIds: List<String>,
        semesterId: String,
    ): Response<AttendifyApiResponse<List<NoParentEmailStudents>?>>

    suspend fun getActiveAttendanceSheet(
        studentId: String,
        divisionId: String,
    ): Response<AttendifyApiResponse<List<Attendance>?>>
}