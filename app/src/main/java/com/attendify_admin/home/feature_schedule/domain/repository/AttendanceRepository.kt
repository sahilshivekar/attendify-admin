package com.attendify_admin.home.feature_schedule.domain.repository

import com.attendify_admin.common.data.remote.dto.response.AttendifyApiResponse
import com.attendify_admin.common.data.remote.dto.response.AttendanceAllStudentsDto
import com.attendify_admin.common.data.remote.dto.response.AttendanceDto
import com.attendify_admin.common.data.remote.dto.response.AttendanceStudentAggregatedAndDetailedAttendanceDto
import com.attendify_admin.common.data.remote.dto.response.AttendanceStudentDto
import com.attendify_admin.common.data.remote.dto.response.NoParentEmailStudentsDto
import com.attendify_admin.home.feature_schedule.data.remote.dto.request.AddStudentsAttendanceRequest
import com.attendify_admin.home.feature_schedule.data.remote.dto.request.CreateAttendanceRequest
import com.attendify_admin.home.feature_schedule.data.remote.dto.request.RemoveAttendanceRequest
import com.attendify_admin.home.feature_schedule.data.remote.dto.request.UpdateStudentAttendanceRequest
import retrofit2.Response

interface AttendanceRepository {
    suspend fun createAttendance(requestBody: CreateAttendanceRequest): Response<AttendifyApiResponse<AttendanceDto?>>

    suspend fun addStudentsAttendance(requestBody: AddStudentsAttendanceRequest): Response<AttendifyApiResponse<List<AttendanceStudentDto>?>>

    suspend fun updateStudentAttendance(requestBody: UpdateStudentAttendanceRequest): Response<AttendifyApiResponse<AttendanceStudentDto?>>

    suspend fun removeAttendance(requestBody: RemoveAttendanceRequest): Response<AttendifyApiResponse<String?>>

    suspend fun getAttendance(
        date: String?,
        attendanceId: Int?,
        classId: Int?,
        studentId: Int?,
        courseId: Int?,
        semesterId: Int?,
        divisionId: Int?,
    ): Response<AttendifyApiResponse<List<AttendanceStudentDto>?>>

    suspend fun getAttendanceOfStudent(
        studentId: Int,
        courseId: Int,
        semesterId: Int,
        divisionId: Int,
        batchId: Int,
        startDate: String,
        endDate: String,
    ): Response<AttendifyApiResponse<AttendanceStudentAggregatedAndDetailedAttendanceDto>>

    suspend fun getAttendanceOfAllForSemesterDivisionBatchCourse(
        courseId: Int,
        semesterId: Int,
        divisionId: Int,
        batchId: Int,
        startDate: String,
        endDate: String,
    ): Response<AttendifyApiResponse<AttendanceAllStudentsDto>>

    suspend fun markStudentAttendanceByBLEsessionUUID(
        bleSessionUUID: String,
        studentId: Int,
    ): Response<AttendifyApiResponse<String?>>

    suspend fun sendAttendanceReport(
        startDate: String,
        endDate: String,
        studentIds: List<String>,
        courseIds: List<String>,
        semesterId: Int,
    ): Response<AttendifyApiResponse<List<NoParentEmailStudentsDto>?>>

    suspend fun getActiveAttendanceSheet(
        studentId: Int,
        divisionId: Int,
    ): Response<AttendifyApiResponse<List<AttendanceDto>?>>
}

