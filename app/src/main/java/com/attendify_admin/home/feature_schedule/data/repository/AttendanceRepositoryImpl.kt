package com.attendify_admin.home.feature_schedule.data.repository


import com.attendify_admin.common.data.remote.dto.response.AttendifyApiResponse
import com.attendify_admin.common.data.remote.dto.response.AttendanceAllStudentsDto
import com.attendify_admin.common.data.remote.dto.response.AttendanceDto
import com.attendify_admin.common.data.remote.dto.response.AttendanceStudentAggregatedAndDetailedAttendanceDto
import com.attendify_admin.common.data.remote.dto.response.AttendanceStudentDto
import com.attendify_admin.common.data.remote.dto.response.NoParentEmailStudentsDto
import com.attendify_admin.home.feature_schedule.data.remote.AttendanceApi
import com.attendify_admin.home.feature_schedule.data.remote.dto.request.AddStudentsAttendanceRequest
import com.attendify_admin.home.feature_schedule.data.remote.dto.request.CreateAttendanceRequest
import com.attendify_admin.home.feature_schedule.data.remote.dto.request.MarkAttendanceRequest
import com.attendify_admin.home.feature_schedule.data.remote.dto.request.RemoveAttendanceRequest
import com.attendify_admin.home.feature_schedule.data.remote.dto.request.SendAttendanceReportRequest
import com.attendify_admin.home.feature_schedule.data.remote.dto.request.UpdateStudentAttendanceRequest
import com.attendify_admin.home.feature_schedule.domain.repository.AttendanceRepository
import retrofit2.Response
import javax.inject.Inject

class AttendanceRepositoryImpl @Inject constructor(
    private val attendanceApi: AttendanceApi,
) : AttendanceRepository {

    override suspend fun createAttendance(requestBody: CreateAttendanceRequest): Response<AttendifyApiResponse<AttendanceDto?>> =
        attendanceApi.createAttendance(requestBody)

    override suspend fun addStudentsAttendance(requestBody: AddStudentsAttendanceRequest): Response<AttendifyApiResponse<List<AttendanceStudentDto>?>> =
        attendanceApi.addStudentsAttendance(requestBody)

    override suspend fun updateStudentAttendance(requestBody: UpdateStudentAttendanceRequest): Response<AttendifyApiResponse<AttendanceStudentDto?>> =
        attendanceApi.updateStudentAttendance(requestBody)

    override suspend fun removeAttendance(requestBody: RemoveAttendanceRequest): Response<AttendifyApiResponse<String?>> =
        attendanceApi.removeAttendance(requestBody.attendanceId)

    override suspend fun getAttendance(
        date: String?,
        attendanceId: Int?,
        classId: Int?,
        studentId: Int?,
        courseId: Int?,
        semesterId: Int?,
        divisionId: Int?,
    ): Response<AttendifyApiResponse<List<AttendanceStudentDto>?>> =
        attendanceApi.getAttendance(
            date,
            attendanceId,
            classId,
            studentId,
            courseId,
            semesterId,
            divisionId
        )

    override suspend fun getAttendanceOfStudent(
        studentId: Int,
        courseId: Int,
        semesterId: Int,
        divisionId: Int,
        batchId: Int,
        startDate: String,
        endDate: String,
    ): Response<AttendifyApiResponse<AttendanceStudentAggregatedAndDetailedAttendanceDto>> =
        attendanceApi.getAttendanceOfStudent(
            studentId,
            courseId,
            semesterId,
            divisionId,
            batchId,
            startDate,
            endDate
        )

    override suspend fun getAttendanceOfAllForSemesterDivisionBatchCourse(
        courseId: Int,
        semesterId: Int,
        divisionId: Int,
        batchId: Int,
        startDate: String,
        endDate: String,
    ): Response<AttendifyApiResponse<AttendanceAllStudentsDto>> =
        attendanceApi.getAttendanceOfAllForSemesterDivisionBatchCourse(
            courseId,
            semesterId,
            divisionId,
            batchId,
            startDate,
            endDate
        )

    override suspend fun markStudentAttendanceByBLEsessionUUID(
        bleSessionUUID: String,
        studentId: Int,
    ): Response<AttendifyApiResponse<String?>> =
        attendanceApi.markStudentAttendanceByBLEsessionUUID(
            MarkAttendanceRequest(
                bleSessionUUID,
                studentId
            )
        )

    override suspend fun sendAttendanceReport(
        startDate: String,
        endDate: String,
        studentIds: List<String>,
        courseIds: List<String>,
        semesterId: Int,
    ): Response<AttendifyApiResponse<List<NoParentEmailStudentsDto>?>> =
        attendanceApi.sendAttendanceReport(
            SendAttendanceReportRequest(
                startDate,
                endDate,
                studentIds,
                courseIds,
                semesterId
            )
        )

    override suspend fun getActiveAttendanceSheet(
        studentId: Int,
        divisionId: Int,
    ): Response<AttendifyApiResponse<List<AttendanceDto>?>> =
        attendanceApi.getActiveAttendanceSheet(
            studentId,
            divisionId
        )
}