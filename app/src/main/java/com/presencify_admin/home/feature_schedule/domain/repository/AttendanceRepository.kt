package com.presencify_admin.home.feature_schedule.domain.repository

import com.presencify_admin.common.data.remote.dto.response.AttendanceAllStudentsDto
import com.presencify_admin.common.data.remote.dto.response.AttendanceDto
import com.presencify_admin.common.data.remote.dto.response.AttendanceStudentAggregatedAndDetailedAttendanceDto
import com.presencify_admin.common.data.remote.dto.response.AttendanceStudentDto
import com.presencify_admin.common.data.remote.dto.response.PresencifyApiResponse
import com.presencify_admin.common.data.remote.dto.response.NoParentEmailStudentsDto
import com.presencify_admin.home.feature_schedule.data.remote.dto.request.AddStudentsAttendanceRequest
import com.presencify_admin.home.feature_schedule.data.remote.dto.request.CreateAttendanceRequest
import com.presencify_admin.home.feature_schedule.data.remote.dto.request.RemoveAttendanceRequest
import com.presencify_admin.home.feature_schedule.data.remote.dto.request.UpdateStudentAttendanceRequest
import retrofit2.Response

interface AttendanceRepository {
    suspend fun createAttendance(requestBody: CreateAttendanceRequest): Response<PresencifyApiResponse<AttendanceDto?>>

    suspend fun addStudentsAttendance(requestBody: AddStudentsAttendanceRequest): Response<PresencifyApiResponse<List<AttendanceStudentDto>?>>

    suspend fun updateStudentAttendance(requestBody: UpdateStudentAttendanceRequest): Response<PresencifyApiResponse<AttendanceStudentDto?>>

    suspend fun removeAttendance(requestBody: RemoveAttendanceRequest): Response<PresencifyApiResponse<String?>>

    suspend fun getAttendance(
        date: String?,
        attendanceId: Int?,
        classId: Int?,
        studentId: Int?,
        courseId: Int?,
        semesterId: Int?,
        divisionId: Int?,
    ): Response<PresencifyApiResponse<List<AttendanceStudentDto>?>>

    suspend fun getAttendanceOfStudent(
        studentId: Int,
        courseId: Int,
        semesterId: Int,
        divisionId: Int,
        batchId: Int,
        startDate: String,
        endDate: String,
    ): Response<PresencifyApiResponse<AttendanceStudentAggregatedAndDetailedAttendanceDto>>

    suspend fun getAttendanceOfAllForSemesterDivisionBatchCourse(
        courseId: Int,
        semesterId: Int,
        divisionId: Int,
        batchId: Int,
        startDate: String,
        endDate: String,
    ): Response<PresencifyApiResponse<AttendanceAllStudentsDto>>

    suspend fun markStudentAttendanceByBLEsessionUUID(
        bleSessionUUID: String,
        studentId: Int,
    ): Response<PresencifyApiResponse<String?>>

    suspend fun sendAttendanceReport(
        startDate: String,
        endDate: String,
        studentIds: List<String>,
        courseIds: List<String>,
        semesterId: Int,
    ): Response<PresencifyApiResponse<List<NoParentEmailStudentsDto>?>>

    suspend fun getActiveAttendanceSheet(
        studentId: Int,
        divisionId: Int,
    ): Response<PresencifyApiResponse<List<AttendanceDto>?>>
}

